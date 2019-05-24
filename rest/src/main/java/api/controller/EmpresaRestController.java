/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.persistence.dtos.EmpresaDTO;
import api.persistence.dtos.EmpresaResponseDTO;
import api.persistence.entity.Empresa;
import api.persistence.service.CapitalSocialService;
import api.persistence.service.EmpresaService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
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
@RequestMapping("/rest/api/empresa")
public class EmpresaRestController implements Serializable {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaRestController(EmpresaService service) {
        this.empresaService = service;
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> create(@RequestBody @Valid EmpresaDTO dto) {
        Empresa empresa = empresaService.save(dto.toEmpresa());
        return new ResponseEntity(
                new EmpresaResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa))
                , HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmpresaResponseDTO> edit(@RequestBody @Valid EmpresaDTO dto, @RequestParam Long id) {
        Empresa empresa = empresaService.edit(dto.toEmpresa(), id);
        return new ResponseEntity(
                new EmpresaResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa))
                , HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity destroy(@RequestParam Long id) {
        empresaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<EmpresaResponseDTO>> getAllEmpresas() {
        Set<EmpresaResponseDTO> empresas = new HashSet<>();
        empresaService.getAll().forEach(empresa -> {
            empresas.add(new EmpresaResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)));
        });
        return new ResponseEntity(empresas, HttpStatus.OK);
        
    }

    @GetMapping("/paginated")
    public ResponseEntity<Set<EmpresaResponseDTO>> getPaginated(@RequestParam int page, @RequestParam int elements) {
        Set<EmpresaResponseDTO> empresas = new HashSet<>();
        empresaService.getPaginated(page, elements).forEach(empresa -> {
            empresas.add(new EmpresaResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)));
        });
        return new ResponseEntity(empresas, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<EmpresaResponseDTO> findEmpresa(@RequestParam Long id) {
        Empresa empresa = empresaService.getById(id);
        return new ResponseEntity(new EmpresaResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)), HttpStatus.OK);
    }

    @GetMapping("/cnpj")
    public ResponseEntity<EmpresaResponseDTO> findByCnpj(@RequestParam String cnpj) {
        Empresa empresa = empresaService.getByCnpj(cnpj);
        return new ResponseEntity(new EmpresaResponseDTO(empresa,CapitalSocialService.getCapitalSocial(empresa)), HttpStatus.OK);

    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getEmpresaCount() {
        return new ResponseEntity(empresaService.getAll().size(), HttpStatus.OK);
    }

}
