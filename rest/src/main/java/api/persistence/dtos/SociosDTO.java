/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos;

import api.persistence.entity.Empresa;
import api.persistence.entity.Pessoa;
import api.persistence.entity.Socios;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class SociosDTO {
    @NotNull(message = "O Valor da Cota é Obrigatório!")
    private Double valorDaCota;
    @NotNull(message = "A empresa é Obrigatória!")
    private Empresa empresa;
    @NotNull(message = "A pessoa é Obrigatória!")
    private Pessoa pessoa;
    
    public Socios toSocios() {
        return new Socios(valorDaCota, empresa, pessoa);
    }
}
