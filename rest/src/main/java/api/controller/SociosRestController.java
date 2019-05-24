/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.persistence.dtos.SociosDTO;
import api.persistence.dtos.SociosResponseDTO;
import api.persistence.entity.Socios;
import api.persistence.service.SociosService;
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

/**
 *
 * @author Unknow
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
        return new ResponseEntity(new SociosResponseDTO(sociosService.save(dto.toSocios())), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SociosResponseDTO> edit(@RequestBody SociosDTO dto, @RequestParam Long id) {
        return new ResponseEntity(new SociosResponseDTO(sociosService.edit(dto.toSocios(), id)), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity destroy(@RequestParam Long id) {
        sociosService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<SociosResponseDTO>> getAll() {
        Set<SociosResponseDTO> socios = new HashSet<>();
        sociosService.getAll().forEach(socio -> {
            socios.add(new SociosResponseDTO(socio));
        });
        return new ResponseEntity(socios, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Set<SociosResponseDTO>> getPaginated(@RequestParam int page, @RequestParam int elements) {
        Set<SociosResponseDTO> socios = new HashSet<>();
        sociosService.getPaginated(page, elements).forEach(socio -> {
            socios.add(new SociosResponseDTO(socio));
        });
        return new ResponseEntity(socios, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<SociosResponseDTO> findSocios(@RequestParam Long id) {
        return new ResponseEntity(new SociosResponseDTO(sociosService.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getSociosCount() {
        return new ResponseEntity(sociosService.getAll().size(), HttpStatus.OK);
    }

}
