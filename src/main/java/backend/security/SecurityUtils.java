package backend.security;

import backend.exception.UnauthenticatedException;
import backend.user.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static String getCurrentlyAuthenticatedUsername() {
        System.out.println(getCurrentAuthentication().getPrincipal());
        return getCurrentAuthentication().getName();
    }

    public static boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isValidAuthentication(authentication);
    }

    private static Authentication getCurrentAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!isValidAuthentication(authentication)) {
            throw new UnauthenticatedException();
        }
        return authentication;
    }

    private static boolean isValidAuthentication(Authentication authentication) {
        return (authentication != null);
    }
}
