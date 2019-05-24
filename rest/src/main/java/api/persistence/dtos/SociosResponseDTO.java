/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos;

import api.persistence.entity.Socios;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class SociosResponseDTO {
    
    private String nomeSocio;
    private String cpfSocio;
    private String empresaCnpj;
    private BigDecimal valorDaCota;
    
    public SociosResponseDTO(Socios socio) {
        this.nomeSocio = socio.getPessoa().getNome();
        this.cpfSocio = socio.getPessoa().getCpf();
        this.empresaCnpj = socio.getEmpresa().getCnpj();
        this.valorDaCota = socio.getValorDaCota();
    }
}
