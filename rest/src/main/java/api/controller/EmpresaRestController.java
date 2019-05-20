/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.entity.Empresa;
import api.service.EmpresaService;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
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
    
    @Autowired
    private EmpresaService empresaService;
    private Log logger;    
    
    @PostMapping("/rest/api/empresa")
    public ResponseEntity<Empresa> create (@RequestBody Empresa empresa) {
        try {
            return new ResponseEntity(empresaService.save(empresa), HttpStatus.OK);
        } catch(Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/rest/api/empresa/{id}")
    public ResponseEntity<Empresa> edit(@RequestBody Empresa empresa, @PathVariable("id") Integer id) {
       try {
           return new ResponseEntity(empresaService.edit(empresa, id), HttpStatus.OK);
       } catch(Exception e) {
           logger.warn(e);
           return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @DeleteMapping("/rest/api/empresa/{id}")
    public ResponseEntity destroy(@PathVariable("id") Integer id) {
        try {
            empresaService.delete(empresaService.getById(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/rest/api/empresa")
     public ResponseEntity<List<Empresa>> getAllEmpresas() {
        try {
            return new ResponseEntity(empresaService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/rest/api/empresa/{maxResults}/{firstResult}")
    public ResponseEntity<List<Empresa>> getPaginated(
            @PathVariable("maxResults") int maxResults,
            @PathVariable("firstResult")int firstResult) {
       try {
           return new ResponseEntity(empresaService.getPaginated(maxResults, firstResult), HttpStatus.OK);
       } catch (Exception e) {
           logger.warn(e);
           return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
                    
    @GetMapping("/rest/api/empresa/{id}")
    public ResponseEntity<Empresa> findEmpresa(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(empresaService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rest/api/empresa/{cnpj}")
    public ResponseEntity<Empresa> findByCnpj(@PathVariable String cnpj) {
        try {
            return new ResponseEntity(empresaService.getByCnpj(cnpj), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("rest/api/empresa/count")
    public ResponseEntity<Integer> getEmpresaCount() {
        return new ResponseEntity(empresaService.getAll().size(), HttpStatus.OK);
    }
    
}
