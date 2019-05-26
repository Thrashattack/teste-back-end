/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller;

import api.controller.exception.RestException;
import api.persistence.dtos.request.EmpresasDTO;
import api.persistence.dtos.response.EmpresasResponseDTO;
import api.persistence.entity.Empresas;
import api.services.CapitalSocialService;
import api.persistence.services.EmpresasService;
import java.io.Serializable;
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
 * @author Carlos Cunha
 */
@RestController
@RequestMapping("/rest/api/empresa")
public class EmpresasRestController implements Serializable {

    private final EmpresasService empresaService;

    @Autowired
    public EmpresasRestController(EmpresasService service) {
        this.empresaService = service;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid EmpresasDTO dto) {
        try {
            Empresas empresa = empresaService.save(dto.toEmpresa());
            return new ResponseEntity(
                    new EmpresasResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @PutMapping
    public ResponseEntity<EmpresasResponseDTO> edit(@RequestBody @Valid EmpresasDTO dto, @RequestParam Long id) {
        try {
            Empresas empresa = empresaService.edit(dto.toEmpresa(), id);
            return new ResponseEntity(
                    new EmpresasResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @DeleteMapping
    public ResponseEntity destroy(@RequestParam Long id) {
        try {
            empresaService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping
    public ResponseEntity<Set<EmpresasResponseDTO>> getAllEmpresas() {
        try {
            Set<EmpresasResponseDTO> empresas = new HashSet<>();
            empresaService.getAll().forEach(empresa -> {
                empresas.add(new EmpresasResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)));
            });
            return new ResponseEntity(empresas, HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/paginated")
    public ResponseEntity<Set<EmpresasResponseDTO>> getPaginated(@RequestParam int page, @RequestParam int elements) {
        try {
            Set<EmpresasResponseDTO> empresas = new HashSet<>();
            empresaService.getPaginated(page, elements).forEach(empresa -> {
                empresas.add(new EmpresasResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)));
            });
            return new ResponseEntity(empresas, HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<EmpresasResponseDTO> findEmpresa(@RequestParam Long id) {
        try {
            Empresas empresa = empresaService.getById(id);
            return new ResponseEntity(new EmpresasResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @GetMapping("/cnpj")
    public ResponseEntity<EmpresasResponseDTO> findByCnpj(@RequestParam String cnpj) {
        try {
            Empresas empresa = empresaService.getByCnpj(cnpj);
            return new ResponseEntity(new EmpresasResponseDTO(empresa, CapitalSocialService.getCapitalSocial(empresa)), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getEmpresaCount() {
        try {
            return new ResponseEntity(empresaService.getAll().size(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestException(e);
        }

    }

}
