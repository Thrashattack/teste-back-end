/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos;

import api.persistence.entity.Pessoa;
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
public class PessoaDTO {

    @NotBlank(message = "O CPF NAO PODE ESTAR VAZIO!")
    @Size(max = 255)
    private String cpf;
    @NotBlank(message = "O Email NAO PODE ESTAR VAZIO!")
    @Size(max = 255)
    @Email
    private String email;
    @NotBlank(message = "O Nome NAO PODE ESTAR VAZIO!")
    @Size(max = 255)
    private String nome;
    @NotBlank(message = "O Sobrenome NAO PODE ESTAR VAZIO!")
    @Size(max = 255)
    private String sobrenome;

    public PessoaDTO() {

    }

    public Pessoa toPessoa() {
        return new Pessoa(cpf, email, nome, sobrenome);
    }

}
