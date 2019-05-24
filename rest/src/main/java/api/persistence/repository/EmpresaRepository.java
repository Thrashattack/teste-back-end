/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.repository;

import api.persistence.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Unknow
 */
@Repository 
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    Empresa findByCnpj(@Param("cnpj") String cnpj);    
    
}
