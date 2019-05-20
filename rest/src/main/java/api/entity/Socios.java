/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Unknow
 */
@Entity
@Table(name = "socios")
@NamedQueries({
    @NamedQuery(name = "Socios.findAll", query = "SELECT s FROM Socios s")
    , @NamedQuery(name = "Socios.findById", query = "SELECT s FROM Socios s WHERE s.id = :id")
    , @NamedQuery(name = "Socios.findByValorCota", query = "SELECT s FROM Socios s WHERE s.valorDaCota = :valorDaCota")})
public class Socios implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_da_cota")
    private Double valorDaCota;
    @JoinColumn(name = "empresa", referencedColumnName = "id")
    @ManyToOne
    private Empresa empresa;
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    

    public Socios() {
    }

    public Socios(Integer id) {
        this.id = id;
    }

    public Socios(Integer id, double valorDaCota, Empresa id_empresa, Pessoa id_pessoa) {
        this.id = id;
        this.valorDaCota = valorDaCota;
        this.empresa = id_empresa; 
        this.pessoa = id_pessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "api.model.Socios[ id=" + id + " ]";
    }

  

    public Double getValorDaCota() {
        return valorDaCota;
    }

    public void setValorDaCota(Double valorDaCota) {
        this.valorDaCota = valorDaCota;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
}
