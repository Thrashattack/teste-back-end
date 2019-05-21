/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.exceptions;

/**
 *
 * @author Unknow
 */
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjectError {

    private final String message;
    private final String field;
    private final Object parameter;
}
