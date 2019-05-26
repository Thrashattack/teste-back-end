/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.services;

import api.persistence.entity.Empresas;
import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Cunha
 */
@Service
public class CapitalSocialService implements Serializable {

    private static BigDecimal capitalSocial = BigDecimal.ZERO;

    public static BigDecimal getCapitalSocial(Empresas e) {
        setZeroTemp();
        if (e.getSocios().isEmpty() || e.getSocios() == null) {
            return getTemp();
        }
        e.getSocios().forEach(socio -> {
            setTemp(socio.getValorDaCota());
        });
        return getTemp();
    }

    private static BigDecimal getTemp() {
        return capitalSocial;
    }

    private static void setTemp(BigDecimal temp) {
        capitalSocial = capitalSocial.add(temp);
    }

    private static void setZeroTemp() {
        capitalSocial = BigDecimal.ZERO;
    }
}
