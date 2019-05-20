/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.entity;

/**
 *
 * @author Unknow
 */
public class EmpresaComCapital extends Empresa {
    private Double capitalSocial;
        public EmpresaComCapital(Empresa empresa, Double capitalSocial) {
            super.setId(empresa.getId());
            super.setCnpj(empresa.getCnpj());
            super.setEmail(empresa.getEmail());
            super.setNomeFantasia(empresa.getNomeFantasia());
            super.setRazaoSocial(empresa.getRazaoSocial());
            super.setSociosList(empresa.getSociosList());
            this.capitalSocial = capitalSocial;
        }
        public Double getCapitalSocial() {            
            return this.capitalSocial;
        }
        public void setCapitalSocial(Double c) {
            this.capitalSocial = c;
        }
}
