/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos;

import api.persistence.entity.Empresa;
import api.persistence.entity.Pessoa;
import api.persistence.entity.Socios;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class SociosDTO {
    
    private Double valorDaCota;
    private Empresa empresa;
    private Pessoa pessoa;

    public SociosDTO() {

    }

    public Socios toSocios() {
        return new Socios(valorDaCota, empresa, pessoa);
    }
}
