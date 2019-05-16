/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.service;

import api.model.Socios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Unknow
 */
@Stateless
public class SociosFacadeREST extends AbstractFacade<Socios> {

    @PersistenceContext(unitName = "api_rest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public SociosFacadeREST() {
        super(Socios.class);
    }

    @Override
    public void create(Socios entity) {
        super.create(entity);
    }

    public void edit(Integer id, Socios entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public List<Socios> findByCnpj(String cnpj) {
        return super.findAll();
    }
     public Socios findByCpf(String cpf) {
        return super.find(cpf);
    }

    @Override
    public List<Socios> findAll() {
        return super.findAll();
    }

    public List<Socios> findRange(Integer from, Integer to) {
        return super.findRange(new int[]{from, to});
    }

    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
