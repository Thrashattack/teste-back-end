/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import java.io.Serializable;
import api.entity.Socios;
import api.service.SociosService;
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
public class SociosRestController implements Serializable {
    
    @Autowired
    private SociosService sociosService;
    private Log logger;
    
    @PostMapping("/rest/api/socios")
    public ResponseEntity<Socios> create(@RequestBody Socios socios) {
        try {
            return new ResponseEntity(sociosService.save(socios), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/rest/api/socios/{id}")
    public ResponseEntity<Socios> edit(@RequestBody Socios socios, @PathVariable("id")Integer id) {
        try {
            return new ResponseEntity(sociosService.edit(socios, id), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/rest/api/socios/{id}")
    public ResponseEntity destroy(@PathVariable("id") Integer id) {
        try {
            sociosService.delete(sociosService.getById(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rest/api/socios")
    public ResponseEntity<List<Socios>> getAll() {
        try {
            return new ResponseEntity(sociosService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/rest/api/socios/{maxResults}/{firstResult}") 
    public ResponseEntity<List<Socios>> getPaginated(@PathVariable("maxResults") int maxResults, @PathVariable("firstResult") int firstResult) {
        try {
            return new ResponseEntity(sociosService.getPaginated(maxResults, maxResults), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rest/api/socios/{id}")
    public ResponseEntity<Socios> findSocios(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity(sociosService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.warn(e);
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> getSociosCount() {
        return new ResponseEntity(sociosService.getAll().size(), HttpStatus.OK);
    }
    
}
