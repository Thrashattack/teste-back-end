/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos;

import api.persistence.entity.Empresa;

/**
 *
 * @author Unknow
 */
public class EmpresaResponseDTO extends Empresa {

    private Double capitalSocial;

    public EmpresaResponseDTO(Empresa empresa, Double capitalSocial) {
        super(empresa.getId(), empresa.getCnpj(), empresa.getEmail(), empresa.getNomeFantasia(), empresa.getRazaoSocial(), empresa.getSocios());
        this.capitalSocial = capitalSocial;
    }

    public Double getCapitalSocial() {
        return this.capitalSocial;
    }

    public void setCapitalSocial(Double c) {
        this.capitalSocial = c;
    }
}
