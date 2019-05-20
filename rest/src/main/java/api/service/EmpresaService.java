/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.service;

import api.entity.Empresa;
import api.entity.EmpresaComCapital;
import api.repository.EmpresaRepository;
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
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;
    
    public Empresa getById(int id) {
        Empresa empresa = repository.findById(id).get();
        return new EmpresaComCapital(empresa, CapitalSocial.getCapitalSocial(empresa));
    }
    
    public List<Empresa> getAll() {
        List<Empresa> empresasComCapital = new ArrayList<>();
        repository.findAll().forEach(empresa -> {
            empresasComCapital.add(new EmpresaComCapital(empresa, CapitalSocial.getCapitalSocial(empresa)));
        });
        return empresasComCapital;
    }
    
    public List<Empresa> getPaginated(int maxValues, int startValue) {
         List<Empresa> empresasComCapital = new ArrayList<>();
         int count = 0;
         for (Iterator<Empresa> it = repository.findAll().iterator(); it.hasNext() && count <=maxValues; ) {
            Empresa empresa = it.next(); 
            if (count >= startValue) 
                empresasComCapital.add(new EmpresaComCapital(empresa, CapitalSocial.getCapitalSocial(empresa)));
            count++;
         }
         return empresasComCapital;
    }
    
    public Empresa getByCnpj(String cnpj) {
        Empresa empresa = repository.findByCnpj(cnpj);
        return new EmpresaComCapital(empresa, CapitalSocial.getCapitalSocial(empresa));        
    }
    public Empresa save(Empresa empresa) {
        return repository.save(empresa);
    }
    @Transactional(readOnly=false)
    public Empresa edit(Empresa empresa, int id) {
        Empresa oldEmpresa = this.getById(id);
        oldEmpresa.setCnpj(empresa.getCnpj());
        oldEmpresa.setEmail(empresa.getEmail());
        oldEmpresa.setNomeFantasia(empresa.getNomeFantasia());
        oldEmpresa.setRazaoSocial(empresa.getRazaoSocial());
        oldEmpresa.setSocios(empresa.getSocios());
        return oldEmpresa;
        
    }
    public void delete(Empresa empresa) {
        repository.delete(empresa);
    }
}
