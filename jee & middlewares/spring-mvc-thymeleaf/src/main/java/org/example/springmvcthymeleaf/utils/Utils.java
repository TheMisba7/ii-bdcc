package org.example.springmvcthymeleaf.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public final class Utils {
    public static boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
    private Utils() {
    }
}
