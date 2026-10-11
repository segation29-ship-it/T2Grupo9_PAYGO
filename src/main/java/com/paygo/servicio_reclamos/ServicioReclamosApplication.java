package com.paygo.servicio_reclamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicioReclamosApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicioReclamosApplication.class, args);
    }
}