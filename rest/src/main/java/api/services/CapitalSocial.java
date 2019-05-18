/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.services;
import api.model.*;
import java.util.HashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author Unknow
 */
@Service
public class CapitalSocial {
    private static final HashMap<Empresa,Double> capitaisSociaisCache = new HashMap<>();
    public static Double getCapitalSocial(Empresa e) {
        if(capitaisSociaisCache.containsKey(e)) {
            return capitaisSociaisCache.get(e);
        }
        Double capitalSocial = 0.0;
        for(Object socio : e.getSocios().toArray()){
            Socios s = (Socios) socio.getClass().cast(Socios.class);
            capitalSocial += s.getValorDaCota();
        }
        capitaisSociaisCache.put(e, capitalSocial);
        return capitalSocial;
    }
}
