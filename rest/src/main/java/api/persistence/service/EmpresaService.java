/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.service;

import api.persistence.entity.Empresa;
import api.persistence.dtos.EmpresaResponseDTO;
import api.persistence.repository.EmpresaRepository;
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
public class EmpresaService implements Serializable {

    @Autowired
    private EmpresaRepository repository;

    public Empresa getById(int id) {
        Empresa empresa = repository.findById(id).get();
        return new EmpresaResponseDTO(empresa, CapitalSocial.getCapitalSocial(empresa));
    }

    public Set<Empresa> getAll() {
        List<Empresa> empresasComCapital = new ArrayList<>();
        repository.findAll().forEach(empresa -> {
            empresasComCapital.add(new EmpresaResponseDTO(empresa, CapitalSocial.getCapitalSocial(empresa)));
        });
        Set<Empresa> empresasComCapitalSet = new HashSet<>(empresasComCapital);
        return empresasComCapitalSet;
    }

    public Set<Empresa> getPaginated(int maxValues, int startValue) {
        List<Empresa> empresasComCapital = new ArrayList<>();
        int count = 0;
        for (Iterator<Empresa> it = repository.findAll().iterator(); it.hasNext() && count < maxValues;) {
            Empresa empresa = it.next();
            if (count >= startValue) {
                empresasComCapital.add(new EmpresaResponseDTO(empresa, CapitalSocial.getCapitalSocial(empresa)));
            }
            count++;
        }
        return new HashSet<>(empresasComCapital);
    }

    public Empresa getByCnpj(String cnpj) {
        Empresa empresa = repository.findByCnpj(cnpj);
        return new EmpresaResponseDTO(empresa, CapitalSocial.getCapitalSocial(empresa));
    }

    public Empresa save(Empresa empresa) {
        return repository.save(empresa);
    }

    @Transactional(readOnly = false)
    public Empresa edit(Empresa empresa, int id) {
        Empresa oldEmpresa = this.getById(id);
        oldEmpresa.setId(empresa.getId());
        oldEmpresa.setCnpj(empresa.getCnpj());
        oldEmpresa.setEmail(empresa.getEmail());
        oldEmpresa.setNomeFantasia(empresa.getNomeFantasia());
        oldEmpresa.setRazaoSocial(empresa.getRazaoSocial());
        oldEmpresa.setSocios(empresa.getSocios());
        repository.save(oldEmpresa);
        return oldEmpresa;

    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
