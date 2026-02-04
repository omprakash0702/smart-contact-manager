package com.scm.scm20.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm20.entities.Providers;
import com.scm.scm20.entities.User;
import com.scm.scm20.helpers.AppConstants;
import com.scm.scm20.repositories.UserRepo;

@Component
public class OAuthAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public OAuthAuthenticationSuccessHandler(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2AuthenticationToken token =
                (OAuth2AuthenticationToken) authentication;
        OAuth2User oauthUser = token.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        String picture = oauthUser.getAttribute("picture");

        User user = userRepo.findByEmail(email).orElse(null);

        if (user == null) {
            user = User.builder()
                    .userId(UUID.randomUUID().toString())
                    .email(email)
                    .name(name)
                    .profilePic(picture)
                    .password(passwordEncoder.encode(
                            UUID.randomUUID().toString()))
                    .roleList(List.of(AppConstants.ROLE_USER))
                    .enabled(true)
                    .emailVerified(true)
                    .provider(Providers.GOOGLE)
                    .build();

            userRepo.save(user);
        }

        // 🔥 IMPORTANT
        response.sendRedirect("/user/profile");
    }
}
