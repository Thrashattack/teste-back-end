/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.services;

import api.persistence.entity.Empresas;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api.persistence.repository.EmpresasRepository;

/**
 *
 * @author Carlos Cunha
 */
@Service
public class EmpresasService implements Serializable {

    @Autowired
    private EmpresasRepository repository;

    public Empresas getById(Long id) throws Exception {
        return repository.findById(id).get();
    }

    public Set<Empresas> getAll() {
        return new HashSet<>(repository.findAll());
    }

    public Set<Empresas> getPaginated(int page, int elements) throws Exception {
        return new HashSet<>(repository.findAll(PageRequest.of(page, elements)).getContent());
    }

    public Empresas getByCnpj(String cnpj) throws Exception {
        return repository.findByCnpj(cnpj);
    }

    public Empresas save(Empresas empresa) throws Exception {
        return repository.save(empresa);
    }

    @Transactional(readOnly = false)
    public Empresas edit(Empresas empresa, Long id) throws Exception {
        Empresas oldEmpresa = repository.findById(id).get();
        oldEmpresa.setId(empresa.getId());
        oldEmpresa.setCnpj(empresa.getCnpj());
        oldEmpresa.setEmail(empresa.getEmail());
        oldEmpresa.setNomeFantasia(empresa.getNomeFantasia());
        oldEmpresa.setRazaoSocial(empresa.getRazaoSocial());
        oldEmpresa.setSocios(empresa.getSocios());
        repository.save(oldEmpresa);
        return oldEmpresa;

    }

    public void delete(Long id) throws Exception {
        repository.deleteById(id);
    }
}
