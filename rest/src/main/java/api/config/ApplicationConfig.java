/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.config;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Unknow
 */
@SpringBootApplication
@EnableAutoConfiguration
public class ApplicationConfig {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationConfig.class, args);
        
    }   
    
}
