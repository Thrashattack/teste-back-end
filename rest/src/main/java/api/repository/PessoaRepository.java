/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.repository;

import api.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Unknow
 */
@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
    
    Pessoa getByCpf(String cpf);
    
    
}
