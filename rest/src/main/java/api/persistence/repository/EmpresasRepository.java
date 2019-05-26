/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.repository;

import api.persistence.entity.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Cunha
 */
@Repository 
public interface EmpresasRepository extends JpaRepository<Empresas, Long> {
    
    Empresas findByCnpj(@Param("cnpj") String cnpj);    
    
}
