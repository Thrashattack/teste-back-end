/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.service;

import api.model.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Unknow
 */
@Stateless

public class PessoaFacadeREST extends AbstractFacade<Pessoa> {

    @PersistenceContext(unitName = "api_rest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PessoaFacadeREST() {
        super(Pessoa.class);
    }

    @Override
    public void create(Pessoa entity) {
        super.create(entity);
    }

    public void edit(Integer id, Pessoa entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    public Pessoa findByCpf(String cpf) {
        return super.find(cpf);
    }

    @Override
    public List<Pessoa> findAll() {
        return super.findAll();
    }

    public List<Pessoa> findRange(Integer from, Integer to) {
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
