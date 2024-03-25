package org.example.springmvcthymeleaf.utils;

import org.example.springmvcthymeleaf.model.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public final class Utils {
    public static boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + role));
    }

    public static boolean hasRole(String role, List<UserRole> roles) {
        for (UserRole userRole: roles) {
            if (userRole.getName().equals(role))
                return true;
        }
        return false;
    }
    private Utils() {
    }
}
