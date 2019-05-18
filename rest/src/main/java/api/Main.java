/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Unknow
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty("server.servlet.context-path", "/");
        System.setProperty("spring.main.allow-bean-definition-overriding", "true");
        SpringApplication.run(Main.class, args);
        
        
        
    }   
    
}
