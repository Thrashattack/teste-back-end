/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Entity
@Table(name = "socios")
@XmlRootElement
@Getter
@Setter
public class Socios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_da_cota")
    private Double valorDaCota;
    @JoinColumn(name = "empresa", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Empresa empresa;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;

    public Socios() {
    }

    public Socios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Socios(Double valorDaCota, Empresa empresa, Pessoa pessoa) {
        this.valorDaCota = valorDaCota;
        this.empresa = empresa;
        this.pessoa = pessoa;
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
        if (!(object instanceof Socios)) {
            return false;
        }
        Socios other = (Socios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "api.persistence.entity.Socios[ id=" + id + " ]";
    }

}
