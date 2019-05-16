/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;
import api.model.*;
import api.service.*;

import java.util.*;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *
 * @author Unknow
 */
@RestController
public class Controller {
    private final EmpresaFacadeREST empresaRest;
    private final PessoaFacadeREST pessoaRest;
    private final SociosFacadeREST sociosRest;
    
    public Controller() {
        this.empresaRest = new EmpresaFacadeREST();
        this.pessoaRest = new PessoaFacadeREST();
        this.sociosRest = new SociosFacadeREST();
    }
    //Get index
    @RequestMapping( value = "/rest/api", method = RequestMethod.GET)
    public String index() {
        return "Api em funcionamento";
    }
    
    
    //Get all Empresas
    @RequestMapping( value = "/rest/api/empresa", method = RequestMethod.GET)
    public List<Empresa> getEmpresas() {
        return this.empresaRest.findAll();
    }
    //Get Empresas de x até y (paginada)
    @RequestMapping( value = "/rest/api/empresa/{from}/{to}", method = RequestMethod.GET)
    public List<Empresa> getEmpresas(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
        return this.empresaRest.findRange(from,to);
    }
    //Get Empresa por CNPJ
    @RequestMapping( value = "/rest/api/empresa/{cnpj}", method = RequestMethod.GET)    
    public Empresa getEmpresas(@PathVariable("cnpj") String cnpj) {
        return this.empresaRest.findByCnpj(cnpj);
    }
    //Add Empresa
    @RequestMapping( value = "/rest/api/empresa", method = RequestMethod.POST)    
    public Empresa addEmpresa(@RequestBody Empresa empresa) {
        this.empresaRest.create(empresa);
        return empresa;
    }
    //Update Empresa
    @RequestMapping( value = "/rest/api/empresa/{cnpj}", method = RequestMethod.PUT)    
    public Empresa updateEmpresa(@PathVariable String cnpj) {
        Empresa empresa = empresaRest.findByCnpj(cnpj);
        empresaRest.edit(empresa);
        return empresa;
    }
    //Delete Empresa
     @RequestMapping( value = "/rest/api/empresa/{cnpj}", method = RequestMethod.DELETE)    
    public Empresa deleteEmpresa(@PathVariable String cnpj) {
        Empresa empresa = empresaRest.findByCnpj(cnpj);
        empresaRest.remove(empresa.getId());
        return empresa;
    }
    
    
    
    
    
     //Get all Pessoas
    @RequestMapping( value = "/rest/api/pessoas", method = RequestMethod.GET)
    public List<Pessoa> getPessoas() {
        return this.pessoaRest.findAll();
    }
    //Get Pessoa de x até y (paginada)
    @RequestMapping( value = "/rest/api/pessoas/{from}/{to}", method = RequestMethod.GET)
    public List<Pessoa> getPessoas(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
        return this.pessoaRest.findRange(from,to);
    }
    //Get Pessoa por CPF
    @RequestMapping( value = "/rest/api/pessoas/{cpf}", method = RequestMethod.GET)    
    public Pessoa getPessoas(@PathVariable("cpf") String cpf) {
        return this.pessoaRest.findByCpf(cpf);
    }
    //Add Pessoa
    @RequestMapping( value = "/rest/api/pessoas", method = RequestMethod.POST)    
    public Pessoa addPessoa(@RequestBody Pessoa pessoa) {
        this.pessoaRest.create(pessoa);
        return pessoa;
    }
    //Update Pessoa
    @RequestMapping( value = "/rest/api/pessoas/{cpf}", method = RequestMethod.PUT)    
    public Pessoa updatePessoa(@PathVariable String cpf) {
        Pessoa pessoa = pessoaRest.findByCpf(cpf);
        pessoaRest.edit(pessoa);
        return pessoa;
    }
    //Delete Pessoa
     @RequestMapping( value = "/rest/api/pessoas/{cpf}", method = RequestMethod.DELETE)    
    public Pessoa deletePessoa(@PathVariable String cpf) {
        Pessoa pessoa = pessoaRest.findByCpf(cpf);
        pessoaRest.remove(pessoa.getId());
        return pessoa;
        
    }
    
    
    
    
    
     //Get all Socios
    @RequestMapping( value = "/rest/api/socios", method = RequestMethod.GET)
    public List<Socios> getSocios() {
        return this.sociosRest.findAll();
    }
    //Get Socios de x até y (paginada)
    @RequestMapping( value = "/rest/api/socios/{from}/{to}", method = RequestMethod.GET)
    public List<Socios> getSocios(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
        return this.sociosRest.findRange(from,to);
    }
    //Get Socios por CPF
    @RequestMapping( value = "/rest/api/socios/{cpf}", method = RequestMethod.GET)    
    public Socios getSociosF(@PathVariable("cpf") String cpf) {
        return this.sociosRest.findByCpf(cpf);
    }
    //Get Socios por CNPJ
    @RequestMapping( value = "/rest/api/socios/{cpf}", method = RequestMethod.GET)    
    public List<Socios> getSociosJ(@PathVariable("cnpj") String cnpj) {
        return this.sociosRest.findByCnpj(cnpj);
    }
    //Add Socio
    @RequestMapping( value = "/rest/api/socios", method = RequestMethod.POST)    
    public Socios addSocio(@RequestBody Socios socio) {
        this.sociosRest.create(socio);
        Empresa e = empresaRest.find(socio.getIdEmpresa());
        e.updateCapitalSocial();
        return socio;
    }
    //Update Socio
    @RequestMapping( value = "/rest/api/socios/{cpf}", method = RequestMethod.PUT)    
    public Socios updateSocio(@PathVariable String cpf) {
        Socios socio = sociosRest.findByCpf(cpf);
        sociosRest.edit(socio);
        Empresa e = empresaRest.find(socio.getIdEmpresa());
        e.updateCapitalSocial();
        return socio;
    }
    //Delete Socio
     @RequestMapping( value = "/rest/api/socios/{cnpj}", method = RequestMethod.DELETE)    
        public Socios deleteSocio(@PathVariable String cpf) {
        Socios socio = sociosRest.findByCpf(cpf);
        sociosRest.remove(socio.getId());
        Empresa e = empresaRest.find(socio.getIdEmpresa());
        e.updateCapitalSocial();
        return socio;
        
    }
    
    
    
   
    
}
