/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos.response;

import api.persistence.entity.Empresas;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Carlos Cunha
 *
 */
@Getter
@Setter
public class EmpresasResponseDTO {

    private String cnpj, email, nomeFantasia, razaoSocial;
    private Set<SociosResponseDTO> socios;
    private BigDecimal capitalSocial;

    public EmpresasResponseDTO(Empresas empresa, BigDecimal capital) {
        this.socios = new HashSet<>();
        this.cnpj = empresa.getCnpj();
        this.email = empresa.getEmail();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.razaoSocial = empresa.getRazaoSocial();
        empresa.getSocios().forEach(socio -> {
            this.socios.add(new SociosResponseDTO(socio));
        });
        this.capitalSocial = capital;
    }
}
