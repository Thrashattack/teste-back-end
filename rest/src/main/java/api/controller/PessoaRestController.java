/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.persistence.entity.Pessoa;
import api.persistence.service.PessoaService;
import java.util.List;
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
public class PessoaRestController {
    
    @Autowired
    private PessoaService pessoaService;
    @PostMapping("/rest/api/pessoa")
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
        try {
            return new ResponseEntity(pessoaService.save(pessoa), HttpStatus.OK);
        } catch (Exception e) {
           System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/rest/api/pessoa/{id}")
    public ResponseEntity<Pessoa> edit(@RequestBody Pessoa pessoa, @PathVariable("id") Integer id) {
       try {
           return new ResponseEntity(pessoaService.edit(pessoa, id), HttpStatus.OK);           
       } catch(Exception e) {
          System.out.println(e);
           return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @DeleteMapping("/rest/api/pessoa/{id}")
    public ResponseEntity destroy(@PathVariable("id") Integer id) {
       try {
           pessoaService.delete(pessoaService.getById(id));
           return new ResponseEntity(HttpStatus.OK);
       } catch (Exception e) {
          System.out.println(e);
           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @GetMapping("/rest/api/pessoa")
    public ResponseEntity<List<Pessoa>> getAll() {
        try {
            return new ResponseEntity(pessoaService.getAll(), HttpStatus.OK);
        } catch (Exception e) {            
           System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rest/api/pessoa/{maxResults}/{firstResult}")
    public ResponseEntity<List<Pessoa>> getPaginated(@PathVariable("maxResults") int maxResults, @PathVariable("firstResult") int firstResult) {
        try {
            return new ResponseEntity(pessoaService.getPaginated(maxResults, firstResult), HttpStatus.OK);
        } catch (Exception e) {
           System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rest/api/pessoa/{id}")
    public ResponseEntity<Pessoa> findPessoa(@PathVariable ("id") Integer id) {        
        try {
            return new ResponseEntity(pessoaService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
           System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("rest/api/pessoa/cpf/{cpf}") 
    public ResponseEntity<Pessoa> findByCpf(@PathVariable("cpf") String cpf) {
        try {
            return new ResponseEntity(pessoaService.getByCpf(cpf), HttpStatus.OK);
        } catch (Exception e) {
           System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getPessoaCount() {
        return new ResponseEntity(pessoaService.getAll().size(), HttpStatus.OK);
    }
    
}
