package org.navistack.framework.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Getter
    @Setter
    private TokenFilter tokenFilter;

    @Getter
    @Setter
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Override
    public void configure(HttpSecurity http) {
        if (this.tokenAuthenticationProvider != null) {
            http.authenticationProvider(this.tokenAuthenticationProvider);
        }
        if (this.tokenFilter == null) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            this.tokenFilter = new TokenFilter(authenticationManager);
        }
        http.addFilterBefore(this.tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
