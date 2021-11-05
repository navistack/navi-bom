package org.navistack.boot.autoconfigure.security;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String issue(Authentication authentication);
    Authentication authenticate(String token);
    boolean validate(String token);
}
