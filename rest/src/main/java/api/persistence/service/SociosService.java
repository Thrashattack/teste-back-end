/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.service;

import api.persistence.entity.Socios;
import api.persistence.repository.SociosRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Socios getById(int id) {
        return repository.findById(id).get();
    }

    public Set<Socios> getAll() {
        List<Socios> findall = (List<Socios>) repository.findAll();
        return new HashSet<>(findall);
    }

    public Socios save(Socios pessoa) {
        return repository.save(pessoa);
    }

    public Set<Socios> getPaginated(int maxValues, int startValue) {
        List<Socios> socios = new ArrayList<>();
        int count = 0;
        for (Iterator<Socios> it = repository.findAll().iterator(); it.hasNext() && count < maxValues;) {
            Socios socio = it.next();
            if (count >= startValue) {
                socios.add(socio);
            }
            count++;
        }
        return new HashSet<>(socios);
    }

    @Transactional(readOnly = false)
    public Socios edit(Socios pessoa, Integer id) {
        Socios oldPessoa = this.getById(id);
        oldPessoa.setEmpresa(pessoa.getEmpresa());
        oldPessoa.setPessoa(pessoa.getPessoa());
        oldPessoa.setValorDaCota(pessoa.getValorDaCota());

        return oldPessoa;

    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
