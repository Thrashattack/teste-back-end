/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.service;

import api.persistence.entity.Pessoa;
import api.persistence.repository.PessoaRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public Pessoa getById(int id) {
        return repository.findById(id).get();
    }
    
    public List<Pessoa> getAll() {
       return (List<Pessoa>) repository.findAll();
    }
    
    public Pessoa getByCpf (String cpf) {
      return repository.findByCpf(cpf);
    }
    public List<Pessoa> getPaginated(int maxValues, int startValue) {
         List<Pessoa> pessoas = new ArrayList<>();
         int count = 0;
         for (Iterator<Pessoa> it = repository.findAll().iterator(); it.hasNext() && count <=maxValues; ) {
            Pessoa pessoa = it.next(); 
            if (count >= startValue) 
                pessoas.add(pessoa);
            count++;
         }
         return pessoas;
    }
    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }
    @Transactional(readOnly=false)
    public Pessoa edit(Pessoa pessoa, Integer id) {
        Pessoa oldPessoa = this.getById(id);
        oldPessoa.setCpf(pessoa.getCpf());
        oldPessoa.setEmail(pessoa.getEmail());
        oldPessoa.setNome(pessoa.getNome());
        oldPessoa.setSobrenome(pessoa.getSobrenome());
        
        return oldPessoa;
        
    }
    public void delete(Pessoa pessoa) {
        repository.delete(pessoa);
    }
    
}
