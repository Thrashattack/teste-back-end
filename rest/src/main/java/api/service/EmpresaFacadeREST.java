/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.service;

import api.model.Empresa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Unknow
 */
@Stateless
public class EmpresaFacadeREST extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "api_rest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EmpresaFacadeREST() {
        super(Empresa.class);
    }

    @Override
    public void create(Empresa entity) {
        super.create(entity);
    }

    @Override
    public void edit(Empresa entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Empresa findByCnpj(String cnpj) {
        return super.find(cnpj);
    }

    @Override
    public List<Empresa> findAll() {
        return super.findAll();
    }

    public List<Empresa> findRange(Integer from, Integer to) {
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
