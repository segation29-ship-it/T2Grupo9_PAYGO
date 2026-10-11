package com.cibertec.msatencioncliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsAtencionClienteApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsAtencionClienteApplication.class, args);
    }
}
