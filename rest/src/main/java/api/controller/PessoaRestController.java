/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.persistence.dtos.PessoaDTO;
import api.persistence.service.PessoaService;
import java.util.Set;
import javax.validation.Valid;
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
    
    
    private final PessoaService pessoaService;
    
    @Autowired
    public PessoaRestController(PessoaService service) {
        this.pessoaService = service;
    }
    @PostMapping("/rest/api/pessoa")
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaDTO dto) {
        return new ResponseEntity(pessoaService.save(dto.toPessoa()), HttpStatus.OK);        
    }
    @PutMapping("/rest/api/pessoa/{id}")
    public ResponseEntity<PessoaDTO> edit(@RequestBody @Valid PessoaDTO dto, @PathVariable("id") Integer id) {       
        return new ResponseEntity(pessoaService.edit(dto.toPessoa(), id), HttpStatus.OK); 
    }
    @DeleteMapping("/rest/api/pessoa/{id}")
    public ResponseEntity destroy(@PathVariable("id") Integer id) {      
        pessoaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);       
    }
    @GetMapping("/rest/api/pessoa")
    public ResponseEntity<Set<PessoaDTO>> getAll() {
        return new ResponseEntity(pessoaService.getAll(), HttpStatus.OK);       
    }
    @GetMapping("/rest/api/pessoa/{maxResults}/{firstResult}")
    public ResponseEntity<Set<PessoaDTO>> getPaginated(@PathVariable("maxResults") int maxResults, @PathVariable("firstResult") int firstResult) {        
        return new ResponseEntity(pessoaService.getPaginated(maxResults, firstResult), HttpStatus.OK);        
    }
    @GetMapping("/rest/api/pessoa/{id}")
    public ResponseEntity<PessoaDTO> findPessoa(@PathVariable ("id") Integer id) {  
        return new ResponseEntity(pessoaService.getById(id), HttpStatus.OK);       
    }
    @GetMapping("rest/api/pessoa/cpf/{cpf}") 
    public ResponseEntity<PessoaDTO> findByCpf(@PathVariable("cpf") String cpf) {        
        return new ResponseEntity(pessoaService.getByCpf(cpf), HttpStatus.OK);       
    }
    @GetMapping("rest/api/pessoa/count")
    public ResponseEntity<Integer> getPessoaCount() {
        return new ResponseEntity(pessoaService.getAll().size(), HttpStatus.OK);
    }
    
}
