/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.persistence.dtos.SociosDTO;
import api.persistence.entity.Socios;
import api.persistence.service.SociosService;
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
public class SociosRestController {
    
    private final SociosService sociosService;
    
    @Autowired
    public SociosRestController(SociosService service) {
        this.sociosService = service;
    }
    
    
    @PostMapping("/rest/api/socios")
    public ResponseEntity<Socios> create(@RequestBody SociosDTO dto) {
        try {
            return new ResponseEntity(sociosService.save(dto.toSocios()), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/rest/api/socios/{id}")
    public ResponseEntity<Socios> edit(@RequestBody SociosDTO dto, @PathVariable("id")Integer id) {
        try {
            return new ResponseEntity(sociosService.edit(dto.toSocios(), id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/rest/api/socios/{id}")
    public ResponseEntity destroy(@PathVariable("id") Integer id) {
        try {
            sociosService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rest/api/socios")
    public ResponseEntity<Set<Socios>> getAll() {
        try {
            return new ResponseEntity(sociosService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/rest/api/socios/{maxResults}/{firstResult}") 
    public ResponseEntity<Set<Socios>> getPaginated(@PathVariable("maxResults") int maxResults, @PathVariable("firstResult") int firstResult) {
        try {
            return new ResponseEntity(sociosService.getPaginated(maxResults, maxResults), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rest/api/socios/{id}")
    public ResponseEntity<Socios> findSocios(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(sociosService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getSociosCount() {
        return new ResponseEntity(sociosService.getAll().size(), HttpStatus.OK);
    }
    
}
