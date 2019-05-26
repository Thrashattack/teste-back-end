/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.controller.exception.RestException;
import api.persistence.dtos.request.PessoasDTO;
import api.persistence.services.PessoasService;
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
 * @author Carlos Cunha
 */
@RestController
@RequestMapping("/rest/api/pessoa")
public class PessoasRestController {

    private final PessoasService pessoaService;

    @Autowired
    public PessoasRestController(PessoasService service) {
        this.pessoaService = service;
    }

    @PostMapping
    public ResponseEntity<PessoasDTO> create(@RequestBody @Valid PessoasDTO dto) {
        try {
            return new ResponseEntity(pessoaService.save(dto.toPessoa()), HttpStatus.OK);
        } catch (Exception e) {
           throw new RestException(e);
        }
    }

    @PutMapping
    public ResponseEntity<PessoasDTO> edit(@RequestBody @Valid PessoasDTO dto, @RequestParam Long id) {
        try {
            return new ResponseEntity(pessoaService.edit(dto.toPessoa(), id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @DeleteMapping
    public ResponseEntity destroy(@RequestParam Long id) {
        try {
            pessoaService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping
    public ResponseEntity<Set<PessoasDTO>> getAll() {
        try {
            return new ResponseEntity(pessoaService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/paginated")
    public ResponseEntity<Set<PessoasDTO>> getPaginated(@RequestParam int page, @RequestParam int elements) {
        try {
            return new ResponseEntity(pessoaService.getPaginated(page, elements), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/id")
    public ResponseEntity<PessoasDTO> findPessoa(@RequestParam Long id) {
        try {
            return new ResponseEntity(pessoaService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/cpf")
    public ResponseEntity<PessoasDTO> findByCpf(@RequestParam String cpf) {
        try {
            return new ResponseEntity(pessoaService.getByCpf(cpf), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getPessoaCount() {
        try {
            return new ResponseEntity(pessoaService.getAll().size(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

}
