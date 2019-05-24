/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.persistence.service;

import api.persistence.entity.Empresa;
import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 *
 * @author Unknow
 */
@Service
public class CapitalSocialService implements Serializable {

    private static BigDecimal capitalSocial = BigDecimal.ZERO;

    public static BigDecimal getCapitalSocial(Empresa e) {
        CapitalSocialService.setZeroTemp();
        if (e.getSocios().isEmpty() || e.getSocios() == null)
            return CapitalSocialService.getTemp();
        e.getSocios().forEach(socio -> {
            CapitalSocialService.setTemp(socio.getValorDaCota());
        });
        return CapitalSocialService.getTemp();
    }

    private static BigDecimal getTemp() {
        return CapitalSocialService.capitalSocial;
    }

    private static void setTemp(BigDecimal temp) {
        CapitalSocialService.capitalSocial = CapitalSocialService.capitalSocial.add(temp);
    }

    private static void setZeroTemp() {
        CapitalSocialService.capitalSocial = BigDecimal.ZERO;
    }
}
