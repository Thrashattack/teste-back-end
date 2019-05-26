/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Carlos Cunha
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Empresas implements Serializable {

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "email")
    private String email;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "razao_social")
    private String razaoSocial;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToMany(mappedBy="empresa")
    @JsonManagedReference
    private Set<Socios> socios;

    public Empresas() {
    }

    public Empresas(Long id) {
        this.id = id;
    }

    public Empresas(String cnpj, String email, String razaoSocial, String nomeFantasia) {
        this.cnpj = cnpj;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.socios = new HashSet<>();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
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
