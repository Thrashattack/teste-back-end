/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos;
import api.persistence.entity.Empresa;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class EmpresaDTO {
    @NotNull(message = "O CNPJ NAO PODE ESTAR VAZIO!")
    private String cnpj;
    @NotNull (message = "O EMAIL NAO PODE ESTAR VAZIO!")
    private String email;
    @NotNull (message = "A RAZAO SOCIAL NAO PODE ESTAR VAZIA!")
    private String razaoSocial;
    @NotNull (message = "O NOME FANTASIA NAO PODE ESTAR VAZIO!")
    private String nomeFantasia;
    
    public Empresa toEmpresa() {
       return new Empresa(cnpj, email, razaoSocial, nomeFantasia);
        
    }
}
