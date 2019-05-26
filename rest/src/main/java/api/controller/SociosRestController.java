/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.controller.exception.RestException;
import api.persistence.dtos.request.SociosDTO;
import api.persistence.dtos.response.SociosResponseDTO;
import api.persistence.services.SociosService;
import java.util.HashSet;
import java.util.Set;
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
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Carlos Cunha
 */
@RestController
@RequestMapping("/rest/api/socios")
public class SociosRestController {

    private final SociosService sociosService;

    @Autowired
    public SociosRestController(SociosService service) {
        this.sociosService = service;
    }

    @PostMapping
    public ResponseEntity<SociosResponseDTO> create(@RequestBody SociosDTO dto) {
        try {
            return new ResponseEntity(new SociosResponseDTO(sociosService.save(dto.toSocios())), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @PutMapping
    public ResponseEntity<SociosResponseDTO> edit(@RequestBody SociosDTO dto, @RequestParam Long id) {
        try {
            return new ResponseEntity(new SociosResponseDTO(sociosService.edit(dto.toSocios(), id)), HttpStatus.OK);
        } catch (Exception e) {
           throw new RestException(e);
        }

    }

    @DeleteMapping
    public ResponseEntity destroy(@RequestParam Long id) {
        try {
            sociosService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping
    public ResponseEntity<Set<SociosResponseDTO>> getAll() {
        try {
            Set<SociosResponseDTO> socios = new HashSet<>();
            sociosService.getAll().forEach(socio -> {
                socios.add(new SociosResponseDTO(socio));
            });
            return new ResponseEntity(socios, HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/paginated")
    public ResponseEntity<Set<SociosResponseDTO>> getPaginated(@RequestParam int page, @RequestParam int elements) {
        try {
            Set<SociosResponseDTO> socios = new HashSet<>();
            sociosService.getPaginated(page, elements).forEach(socio -> {
                socios.add(new SociosResponseDTO(socio));
            });
            return new ResponseEntity(socios, HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/id")
    public ResponseEntity<SociosResponseDTO> findSocios(@RequestParam Long id) {
        try {
            return new ResponseEntity(new SociosResponseDTO(sociosService.getById(id)), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getSociosCount() {
        try {
            return new ResponseEntity(sociosService.getAll().size(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

}
