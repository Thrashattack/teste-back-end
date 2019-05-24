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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Unknow
 */
@RestController
@RequestMapping("/rest/api/pessoa")
public class PessoaRestController {
    
    
    private final PessoaService pessoaService;
    
    @Autowired
    public PessoaRestController(PessoaService service) {
        this.pessoaService = service;
    }
    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaDTO dto) {
        return new ResponseEntity(pessoaService.save(dto.toPessoa()), HttpStatus.OK);        
    }
    @PutMapping
    public ResponseEntity<PessoaDTO> edit(@RequestBody @Valid PessoaDTO dto, @RequestParam Long id) {       
        return new ResponseEntity(pessoaService.edit(dto.toPessoa(), id), HttpStatus.OK); 
    }
    @DeleteMapping
    public ResponseEntity destroy(@RequestParam Long id) {      
        pessoaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);       
    }
    @GetMapping
    public ResponseEntity<Set<PessoaDTO>> getAll() {
        return new ResponseEntity(pessoaService.getAll(), HttpStatus.OK);       
    }
    @GetMapping("/paginated")
    public ResponseEntity<Set<PessoaDTO>> getPaginated(@RequestParam int page, @RequestParam int elements) {        
        return new ResponseEntity(pessoaService.getPaginated(page, elements), HttpStatus.OK);        
    }
    @GetMapping("/id")
    public ResponseEntity<PessoaDTO> findPessoa(@RequestParam Long id) {  
        return new ResponseEntity(pessoaService.getById(id), HttpStatus.OK);       
    }
    @GetMapping("/cpf")
    public ResponseEntity<PessoaDTO> findByCpf(@RequestParam String cpf) {        
        return new ResponseEntity(pessoaService.getByCpf(cpf), HttpStatus.OK);       
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> getPessoaCount() {
        return new ResponseEntity(pessoaService.getAll().size(), HttpStatus.OK);
    }
    
}
