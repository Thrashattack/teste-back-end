/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.controller.exceptions.IllegalOrphanException;
import api.controller.exceptions.NonexistentEntityException;
import api.model.*;
import api.services.CapitalSocial;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Unknow
 */
@RestController
public class EmpresaJpaController implements Serializable {
    
    public EmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
   
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    @PostMapping("/rest/api/empresa")
    public void create(@RequestBody Empresa empresa) {
        System.out.println(empresa);
        if (empresa.getSocios() == null) {
            empresa.setSocios(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Socios> attachedSociosCollection = new ArrayList<>();
            for (Socios sociosCollectionSociosToAttach : empresa.getSocios()) {
                sociosCollectionSociosToAttach = em.getReference(sociosCollectionSociosToAttach.getClass(), sociosCollectionSociosToAttach.getId());
                attachedSociosCollection.add(sociosCollectionSociosToAttach);
            }
            empresa.setSocios(attachedSociosCollection);
            em.persist(empresa);
            for (Socios sociosCollectionSocios : empresa.getSocios()) {
                Empresa oldIdEmpresaOfSociosCollectionSocios = sociosCollectionSocios.getEmpresa();
                sociosCollectionSocios.setEmpresa(empresa);
                sociosCollectionSocios = em.merge(sociosCollectionSocios);
                if (oldIdEmpresaOfSociosCollectionSocios != null) {
                    oldIdEmpresaOfSociosCollectionSocios.getSocios().remove(sociosCollectionSocios);
                    oldIdEmpresaOfSociosCollectionSocios = em.merge(oldIdEmpresaOfSociosCollectionSocios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    @PutMapping("/rest/api/empresa")
    public void edit(@RequestBody Empresa empresa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getId());
            Collection<Socios> sociosCollectionOld = persistentEmpresa.getSocios();
            Collection<Socios> sociosCollectionNew = empresa.getSocios();
            List<String> illegalOrphanMessages = null;
            for (Socios sociosCollectionOldSocios : sociosCollectionOld) {
                if (!sociosCollectionNew.contains(sociosCollectionOldSocios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Socios " + sociosCollectionOldSocios + " since its idEmpresa field is not nullable.");
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
            empresa.setSocios(sociosCollectionNew);
            empresa = em.merge(empresa);
            for (Socios sociosCollectionNewSocios : sociosCollectionNew) {
                if (!sociosCollectionOld.contains(sociosCollectionNewSocios)) {
                    Empresa oldIdEmpresaOfSociosCollectionNewSocios = sociosCollectionNewSocios.getEmpresa();
                    sociosCollectionNewSocios.setEmpresa(empresa);
                    sociosCollectionNewSocios = em.merge(sociosCollectionNewSocios);
                    if (oldIdEmpresaOfSociosCollectionNewSocios != null && !oldIdEmpresaOfSociosCollectionNewSocios.equals(empresa)) {
                        oldIdEmpresaOfSociosCollectionNewSocios.getSocios().remove(sociosCollectionNewSocios);
                        oldIdEmpresaOfSociosCollectionNewSocios = em.merge(oldIdEmpresaOfSociosCollectionNewSocios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresa.getId();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    @DeleteMapping("/rest/api/empresa")
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Socios> sociosCollectionOrphanCheck = empresa.getSocios();
            for (Socios sociosCollectionOrphanCheckSocios : sociosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Empresa (" + empresa + ") cannot be destroyed since the Socios " + sociosCollectionOrphanCheckSocios + " in its sociosCollection field has a non-nullable idEmpresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @GetMapping("/rest/api/empresa")
    public ArrayList<EmpresaComCapital> findEmpresaEntities() {
        ArrayList<Empresa> arrayEmpresas = findEmpresaEntities(true, -1, -1);
        ArrayList<EmpresaComCapital> arrayEmpresasCc = new ArrayList<>();           
        arrayEmpresas.forEach((empresa) -> {
            arrayEmpresasCc.add(new EmpresaComCapital(empresa, CapitalSocial.getCapitalSocial(empresa)));
        });
        return arrayEmpresasCc;
    }
    
    @GetMapping("/rest/api/empresa/{maxResults}/{firstResult}")
    public List<EmpresaComCapital> findEmpresaEntities(
            @PathVariable("maxResults") int maxResults,
            @PathVariable("firstResult")int firstResult) {
        ArrayList<Empresa> arrayEmpresas = findEmpresaEntities(false, maxResults, firstResult);
        ArrayList<EmpresaComCapital> arrayEmpresasCc = new ArrayList<>();
        arrayEmpresas.forEach((empresa) -> {
            arrayEmpresasCc.add(new EmpresaComCapital(empresa, CapitalSocial.getCapitalSocial(empresa)));
        });
        return arrayEmpresasCc;
    }
   
    private ArrayList<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return (ArrayList)q.getResultList();
        } finally {
            em.close();
        }
    }
    @GetMapping("/rest/api/empresa/{id}")
    public EmpresaComCapital findEmpresa(@PathVariable("id") Integer id) {
        EntityManager em = getEntityManager();
        Empresa empresa = em.find(Empresa.class, id);
        try {
            return new EmpresaComCapital(empresa, CapitalSocial.getCapitalSocial(empresa));
        } finally {
            em.close();
        }
    }
    
    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
