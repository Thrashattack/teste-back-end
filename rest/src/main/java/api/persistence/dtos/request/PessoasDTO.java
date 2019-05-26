/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos.request;

import api.persistence.entity.Pessoas;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Carlos Cunha
 */
@Getter
@Setter
public class PessoasDTO {

    
    private String cpf;
    
    private String email;
    
    private String nome;
    
    private String sobrenome;

    public PessoasDTO() {

    }

    public Pessoas toPessoa() throws Exception {
        if(this.cpf == null || this.cpf.equals(""))
            throw new Exception("CPF nao pode estar em branco");
        else if (this.email == null || this.email.equals("") || !(this.email.contains("@")))
            throw new Exception("Email nao pode estar em branco");
        else if (this.nome == null || this.nome.equals(""))
            throw new Exception("Nome nao pode estar em branco");
        else if (this.sobrenome == null || this.sobrenome.equals(""))
            throw new Exception("Sobrenome nao pode estar em branco");
        return new Pessoas(cpf, email, nome, sobrenome);
    }

}
