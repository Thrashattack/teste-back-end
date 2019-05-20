/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.persistence.dtos.EmpresaDTO;
import api.persistence.entity.Empresa;
import api.persistence.service.EmpresaService;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Unknow
 */ 
@RestController
public class EmpresaRestController implements Serializable {
    
    
    private final EmpresaService empresaService;
    
    @Autowired
    public EmpresaRestController(EmpresaService service) {
        this.empresaService = service;
    }
    
    @PostMapping("/rest/api/empresa")
    public ResponseEntity<Empresa> create (@RequestBody EmpresaDTO dto) {
        try {
            return new ResponseEntity(empresaService.save(dto.toEmpresa()), HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/rest/api/empresa/{id}")
    public ResponseEntity<Empresa> edit(@RequestBody EmpresaDTO dto, @PathVariable("id") Integer id) {
       try {
           return new ResponseEntity(empresaService.edit(dto.toEmpresa(), id), HttpStatus.OK);
       } catch(Exception e) {
           System.out.println(e.getMessage());
           return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @DeleteMapping("/rest/api/empresa/{id}")
    public ResponseEntity destroy(@PathVariable("id") Integer id) {
        try {
            empresaService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/rest/api/empresa")
     public ResponseEntity<Set<Empresa>> getAllEmpresas() {
        try {
            return new ResponseEntity(empresaService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/rest/api/empresa/{maxResults}/{firstResult}")
    public ResponseEntity<Set<Empresa>> getPaginated(
            @PathVariable("maxResults") int maxResults,
            @PathVariable("firstResult")int firstResult) {
       try {
           return new ResponseEntity(empresaService.getPaginated(maxResults, firstResult), HttpStatus.OK);
       } catch (Exception e) {
           System.out.println(e.getMessage());
           return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
                    
    @GetMapping("/rest/api/empresa/{id}")
    public ResponseEntity<Empresa> findEmpresa(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(empresaService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rest/api/empresa/cnpj/{cnpj}")
    public ResponseEntity<Empresa> findByCnpj(@PathVariable String cnpj) {
        try {
            return new ResponseEntity(empresaService.getByCnpj(cnpj), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("rest/api/empresa/count")
    public ResponseEntity<Integer> getEmpresaCount() {
        return new ResponseEntity(empresaService.getAll().size(), HttpStatus.OK);
    }
    
}
