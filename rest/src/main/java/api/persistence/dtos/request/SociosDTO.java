/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos.request;

import api.persistence.entity.Empresas;
import api.persistence.entity.Pessoas;
import api.persistence.entity.Socios;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Carlos Cunha
 */
@Getter
@Setter
public class SociosDTO {
    
    private Empresas empresa;
    private Pessoas pessoa;
    private BigDecimal valorDaCota;

    public SociosDTO() {

    }

    public Socios toSocios() throws Exception {
        if(this.empresa == null || this.empresa.getId() == null)
            throw new Exception("Empresa nao pode estar em branco");
        else if (this.pessoa == null || this.pessoa.getId() == null)
            throw new Exception("Pessoa nao pode estar em branco");
        else if (this.valorDaCota == null)
            throw new Exception("Valor da Cota nao pode estar em branco");
        return new Socios(empresa,pessoa,valorDaCota);
    }
}
