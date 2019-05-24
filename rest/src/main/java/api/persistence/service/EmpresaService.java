/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.service;

import api.persistence.entity.Empresa;
import api.persistence.repository.EmpresaRepository;
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
public class EmpresaService implements Serializable {

    @Autowired
    private EmpresaRepository repository;

    public Empresa getById(Long id) {        
        return repository.findById(id).get();        
    }

    public Set<Empresa> getAll() {
        return new HashSet<>(repository.findAll());        
    }

    public Set<Empresa> getPaginated(int page, int elements) {                
        return new HashSet<>(repository.findAll(PageRequest.of(page, elements)).getContent());
    }

    public Empresa getByCnpj(String cnpj) {        
        return repository.findByCnpj(cnpj);
    }

    public Empresa save(Empresa empresa) {
        return repository.save(empresa);
    }

    @Transactional(readOnly = false)
    public Empresa edit(Empresa empresa, Long id) {
        Empresa oldEmpresa = repository.findById(id).get();
        oldEmpresa.setId(empresa.getId());
        oldEmpresa.setCnpj(empresa.getCnpj());
        oldEmpresa.setEmail(empresa.getEmail());
        oldEmpresa.setNomeFantasia(empresa.getNomeFantasia());
        oldEmpresa.setRazaoSocial(empresa.getRazaoSocial());
        oldEmpresa.setSocios(empresa.getSocios());
        repository.save(oldEmpresa);
        return oldEmpresa;

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
