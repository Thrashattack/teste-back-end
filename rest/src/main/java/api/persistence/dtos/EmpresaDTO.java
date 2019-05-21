/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos;

import api.persistence.entity.Empresa;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class EmpresaDTO {

    @NotBlank(message = "O CNPJ NAO PODE ESTAR VAZIO!")
    @Size(max = 255)
    private String cnpj;
    @NotBlank(message = "O EMAIL NAO PODE ESTAR VAZIO!")
    @Email
    @Size(max = 255)
    private String email;
    @NotBlank(message = "A RAZAO SOCIAL NAO PODE ESTAR VAZIA!")
    @Size(max = 255)
    private String razaoSocial;
    @NotBlank(message = "O NOME FANTASIA NAO PODE ESTAR VAZIO!")
    @Size(max = 255)
    private String nomeFantasia;

    public EmpresaDTO() {

    }

    public Empresa toEmpresa() {
        return new Empresa(cnpj, email, razaoSocial, nomeFantasia);

    }
}
