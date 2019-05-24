/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.service;

import api.persistence.entity.Pessoa;
import api.persistence.repository.PessoaRepository;
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
public class PessoaService implements Serializable {

    @Autowired
    private PessoaRepository repository;

    public Pessoa getById(Long id) {
        return repository.findById(id).get();
    }

    public Set<Pessoa> getAll() {
        return new HashSet<>(repository.findAll());
    }

    public Pessoa getByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Set<Pessoa> getPaginated(int page, int elements) {            
        return new HashSet<>(repository.findAll(PageRequest.of(page, elements)).getContent());
    }

    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Transactional(readOnly = false)
    public Pessoa edit(Pessoa pessoa, Long id) {
        Pessoa oldPessoa = this.getById(id);
        oldPessoa.setCpf(pessoa.getCpf());
        oldPessoa.setEmail(pessoa.getEmail());
        oldPessoa.setNome(pessoa.getNome());
        oldPessoa.setSobrenome(pessoa.getSobrenome());

        return oldPessoa;

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
