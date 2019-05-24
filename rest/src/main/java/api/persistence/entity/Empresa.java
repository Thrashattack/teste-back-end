/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Empresa implements Serializable {

    
    @Column(name = "cnpj")
    private String cnpj;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
  
    @Column(name = "razao_social")
    private String razaoSocial;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "empresa")
    @JsonManagedReference
    private Set<Socios> socios;

    public Empresa() {
    }

    public Empresa(Long id) {
        this.id = id;
    }
    
    public Empresa (String cnpj, String email, String razaoSocial, String nomeFantasia) {
        this.cnpj = cnpj;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }
    
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "api.persistence.entity.Empresa[ id=" + id + " ]";
    }

}
