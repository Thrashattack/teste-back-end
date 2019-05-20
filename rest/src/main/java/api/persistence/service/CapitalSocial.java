/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.service;
import api.persistence.entity.Empresa;
import java.io.Serializable;
import java.util.HashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author Unknow
 */
@Service
public class CapitalSocial implements Serializable {
   
    private static Double capitalSocial = 0.0;
    public static Double getCapitalSocial(Empresa e) {
        CapitalSocial.setZeroTemp();        
        e.getSocios().forEach(socio -> {
            CapitalSocial.setTemp(socio.getValorDaCota());
        });     
        return CapitalSocial.getTemp();
    }
    
    private static Double getTemp() {
        return CapitalSocial.capitalSocial;
    }
    private static void setTemp(Double temp) {
        CapitalSocial.capitalSocial += temp;
    }
    private static void setZeroTemp() {
        CapitalSocial.capitalSocial = 0.0;
    }
}
