/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.service;

import api.persistence.entity.Socios;
import api.persistence.repository.SociosRepository;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Unknow
 */
@Service
public class SociosService implements Serializable {

    @Autowired
    private SociosRepository repository;

    public Socios getById(Long id) {
        return repository.findById(id).get();
    }

    public Set<Socios> getAll() {
        return new HashSet<>(repository.findAll());
    }

    public Socios save(Socios pessoa) {
        return repository.save(pessoa);
    }

    public Set<Socios> getPaginated(int page, int elements) {        
        return new HashSet<>(repository.findAll(PageRequest.of(page, elements)).getContent());
    }

    @Transactional(readOnly = false)
    public Socios edit(Socios pessoa, Long id) {
        Socios oldPessoa = this.getById(id);
        oldPessoa.setEmpresa(pessoa.getEmpresa());
        oldPessoa.setPessoa(pessoa.getPessoa());
        oldPessoa.setValorDaCota(pessoa.getValorDaCota());

        return oldPessoa;

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
