/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.controller.exceptions.IllegalOrphanException;
import api.controller.exceptions.NonexistentEntityException;
import api.model.Pessoa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import api.model.Socios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Unknow
 */
@RestController
public class PessoaJpaController implements Serializable {

    public PessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    @PostMapping("/rest/api/pessoa")
    public void create(Pessoa pessoa) {
        if (pessoa.getSociosCollection() == null) {
            pessoa.setSociosCollection(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Socios> attachedSociosCollection = new ArrayList<>();
            for (Socios sociosCollectionSociosToAttach : pessoa.getSociosCollection()) {
                sociosCollectionSociosToAttach = em.getReference(sociosCollectionSociosToAttach.getClass(), sociosCollectionSociosToAttach.getId());
                attachedSociosCollection.add(sociosCollectionSociosToAttach);
            }
            pessoa.setSociosCollection(attachedSociosCollection);
            em.persist(pessoa);
            for (Socios sociosCollectionSocios : pessoa.getSociosCollection()) {
                Pessoa oldIdSocioOfSociosCollectionSocios = sociosCollectionSocios.getPessoa();
                sociosCollectionSocios.setPessoa(pessoa);
                sociosCollectionSocios = em.merge(sociosCollectionSocios);
                if (oldIdSocioOfSociosCollectionSocios != null) {
                    oldIdSocioOfSociosCollectionSocios.getSociosCollection().remove(sociosCollectionSocios);
                    oldIdSocioOfSociosCollectionSocios = em.merge(oldIdSocioOfSociosCollectionSocios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    @PutMapping("/rest/api/pessoa")
    public void edit(Pessoa pessoa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa persistentPessoa = em.find(Pessoa.class, pessoa.getId());
            Collection<Socios> sociosCollectionOld = persistentPessoa.getSociosCollection();
            Collection<Socios> sociosCollectionNew = pessoa.getSociosCollection();
            List<String> illegalOrphanMessages = null;
            for (Socios sociosCollectionOldSocios : sociosCollectionOld) {
                if (!sociosCollectionNew.contains(sociosCollectionOldSocios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Socios " + sociosCollectionOldSocios + " since its idSocio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Socios> attachedSociosCollectionNew = new ArrayList<>();
            for (Socios sociosCollectionNewSociosToAttach : sociosCollectionNew) {
                sociosCollectionNewSociosToAttach = em.getReference(sociosCollectionNewSociosToAttach.getClass(), sociosCollectionNewSociosToAttach.getId());
                attachedSociosCollectionNew.add(sociosCollectionNewSociosToAttach);
            }
            sociosCollectionNew = attachedSociosCollectionNew;
            pessoa.setSociosCollection(sociosCollectionNew);
            pessoa = em.merge(pessoa);
            for (Socios sociosCollectionNewSocios : sociosCollectionNew) {
                if (!sociosCollectionOld.contains(sociosCollectionNewSocios)) {
                    Pessoa oldIdSocioOfSociosCollectionNewSocios = sociosCollectionNewSocios.getPessoa();
                    sociosCollectionNewSocios.setPessoa(pessoa);
                    sociosCollectionNewSocios = em.merge(sociosCollectionNewSocios);
                    if (oldIdSocioOfSociosCollectionNewSocios != null && !oldIdSocioOfSociosCollectionNewSocios.equals(pessoa)) {
                        oldIdSocioOfSociosCollectionNewSocios.getSociosCollection().remove(sociosCollectionNewSocios);
                        oldIdSocioOfSociosCollectionNewSocios = em.merge(oldIdSocioOfSociosCollectionNewSocios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pessoa.getId();
                if (findPessoa(id) == null) {
                    throw new NonexistentEntityException("The pessoa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    @DeleteMapping("/rest/api/pessoa")
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa pessoa;
            try {
                pessoa = em.getReference(Pessoa.class, id);
                pessoa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pessoa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Socios> sociosCollectionOrphanCheck = pessoa.getSociosCollection();
            for (Socios sociosCollectionOrphanCheckSocios : sociosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Pessoa (" + pessoa + ") cannot be destroyed since the Socios " + sociosCollectionOrphanCheckSocios + " in its sociosCollection field has a non-nullable idSocio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    @GetMapping("/rest/api/pessoa")
    public List<Pessoa> findPessoaEntities() {
        return findPessoaEntities(true, -1, -1);
    }
    @GetMapping("/rest/api/pessoa/{maxResults}/{firstResult}")
    public List<Pessoa> findPessoaEntities(int maxResults, int firstResult) {
        return findPessoaEntities(false, maxResults, firstResult);
    }
    
    private List<Pessoa> findPessoaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pessoa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    @GetMapping("/rest/api/pessoa/{id}")
    public Pessoa findPessoa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } finally {
            em.close();
        }
    }

    public int getPessoaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pessoa> rt = cq.from(Pessoa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
