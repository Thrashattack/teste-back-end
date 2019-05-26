/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.dtos.request;

import api.persistence.entity.Empresas;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Carlos Cunha
 */
@Getter
@Setter
public class EmpresasDTO {
    
    
    private String cnpj;
    
    private String email;
  
    private String razaoSocial;
    
    private String nomeFantasia;

    public EmpresasDTO() {
        
    }

    public Empresas toEmpresa() throws Exception{
        if(this.cnpj == null || this.cnpj.equals(""))
            throw new Exception("Cnpj nao pode estar em branco");
        else if (this.email == null || this.email.equals("") || !(this.email.contains("@")))
            throw new Exception("Email nao pode estar em branco");
        else if (this.razaoSocial == null || this.razaoSocial.equals(""))
            throw new Exception("Razao Social nao pode estar em branco");
        else if (this.nomeFantasia == null || this.nomeFantasia.equals(""))
            throw new Exception("Nome Fantasia nao pode estar em branco");
        return new Empresas(cnpj, email, razaoSocial, nomeFantasia);

    }
}
