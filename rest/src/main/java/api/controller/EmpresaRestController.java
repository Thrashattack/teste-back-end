/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.persistence.dtos.EmpresaDTO;
import api.persistence.service.EmpresaService;
import java.io.Serializable;
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
public class EmpresaRestController implements Serializable {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaRestController(EmpresaService service) {
        this.empresaService = service;
    }

    @PostMapping("/rest/api/empresa")
    public ResponseEntity<EmpresaDTO> create(@RequestBody @Valid EmpresaDTO dto) {
        return new ResponseEntity(empresaService.save(dto.toEmpresa()), HttpStatus.OK);
    }

    @PutMapping("/rest/api/empresa/{id}")
    public ResponseEntity<EmpresaDTO> edit(@RequestBody @Valid EmpresaDTO dto, @PathVariable("id") Integer id) {
        return new ResponseEntity(empresaService.edit(dto.toEmpresa(), id), HttpStatus.OK);
    }

    @DeleteMapping("/rest/api/empresa/{id}")
    public ResponseEntity destroy(@PathVariable("id") Integer id) {
        empresaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/rest/api/empresa")
    public ResponseEntity<Set<EmpresaDTO>> getAllEmpresas() {
        return new ResponseEntity(empresaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/rest/api/empresa/{maxResults}/{firstResult}")
    public ResponseEntity<Set<EmpresaDTO>> getPaginated(
            @PathVariable("maxResults") int maxResults,
            @PathVariable("firstResult") int firstResult) {
        return new ResponseEntity(empresaService.getPaginated(maxResults, firstResult), HttpStatus.OK);
    }

    @GetMapping("/rest/api/empresa/{id}")
    public ResponseEntity<EmpresaDTO> findEmpresa(@PathVariable("id") Integer id) {
        return new ResponseEntity(empresaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/rest/api/empresa/cnpj/{cnpj}")
    public ResponseEntity<EmpresaDTO> findByCnpj(@PathVariable String cnpj) {
        return new ResponseEntity(empresaService.getByCnpj(cnpj), HttpStatus.OK);

    }

    @GetMapping("rest/api/empresa/count")
    public ResponseEntity<Integer> getEmpresaCount() {
        return new ResponseEntity(empresaService.getAll().size(), HttpStatus.OK);
    }

}
