package org.navistack.smoketest.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class SampleSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleSecurityApplication.class, args);
    }

}
