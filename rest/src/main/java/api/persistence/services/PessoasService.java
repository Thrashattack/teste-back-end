/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.services;

import api.persistence.entity.Pessoas;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api.persistence.repository.PessoasRepository;

/**
 *
 * @author Carlos Cunha
 */
@Service
public class PessoasService implements Serializable {

    @Autowired
    private PessoasRepository repository;

    public Pessoas getById(Long id) throws Exception {
        return repository.findById(id).get();
    }

    public Set<Pessoas> getAll() throws Exception {
        return new HashSet<>(repository.findAll());
    }

    public Pessoas getByCpf(String cpf) throws Exception {
        return repository.findByCpf(cpf);
    }

    public Set<Pessoas> getPaginated(int page, int elements) throws Exception {
        return new HashSet<>(repository.findAll(PageRequest.of(page, elements)).getContent());
    }

    public Pessoas save(Pessoas pessoa) throws Exception {
        return repository.save(pessoa);
    }

    @Transactional(readOnly = false)
    public Pessoas edit(Pessoas pessoa, Long id) throws Exception {
        Pessoas oldPessoa = this.getById(id);
        oldPessoa.setCpf(pessoa.getCpf());
        oldPessoa.setEmail(pessoa.getEmail());
        oldPessoa.setNome(pessoa.getNome());
        oldPessoa.setSobrenome(pessoa.getSobrenome());

        return oldPessoa;

    }

    public void delete(Long id) throws Exception {
        repository.deleteById(id);
    }

}
