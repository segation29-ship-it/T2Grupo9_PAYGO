package com.cibertec.eurekaserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class T2Grupo9PaygoApplication {
    public static void main(String[] args) {
        SpringApplication.run(T2Grupo9PaygoApplication.class, args);
    }
}
