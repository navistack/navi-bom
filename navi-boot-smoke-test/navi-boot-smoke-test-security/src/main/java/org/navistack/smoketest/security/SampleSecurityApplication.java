package org.navistack.smoketest.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity
public class SampleSecurityApplication {

    static void main(String[] args) {
        SpringApplication.run(SampleSecurityApplication.class, args);
    }

}
