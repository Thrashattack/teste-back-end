/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos;

import api.persistence.entity.Pessoa;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Getter
@Setter
public class PessoaDTO {
    @NotNull(message = "O CPF NAO PODE ESTAR VAZIO!")
    private String cpf;
    @NotNull(message = "O Email NAO PODE ESTAR VAZIO!")
    private String email;    
    @NotNull(message = "O Nome NAO PODE ESTAR VAZIO!")
    private String nome;    
    @NotNull(message = "O Sobrenome NAO PODE ESTAR VAZIO!")
    private String sobrenome;
    
    public Pessoa toPessoa() {
        return new Pessoa(cpf, email, nome, sobrenome);
    }
    
}
