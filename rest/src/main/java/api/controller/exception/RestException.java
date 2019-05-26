/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Unknow
 */
public class RestException extends ResponseStatusException {
    
    public RestException(Exception e) {
        super(HttpStatus.BAD_REQUEST, e.getMessage(), e.initCause(e.getCause()));        
    }
}
