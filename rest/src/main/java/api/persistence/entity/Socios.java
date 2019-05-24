/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Unknow
 */
@Entity
@Getter
@Setter
public class Socios implements Serializable {

    @Basic(optional = false)
    @Column(name = "valor_da_cota")
    private BigDecimal valorDaCota;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "empresa", referencedColumnName = "id")
    @ManyToOne
    @JsonBackReference
    private Empresa empresa;

    public Socios() {
    }

    public Socios(Long id) {
        this.id = id;
    }

    public Socios (BigDecimal valorDaCota, Empresa empresa, Pessoa pessoa) {
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
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "api.persistence.entity.Socios[ id=" + id + " ]";
    }
}


