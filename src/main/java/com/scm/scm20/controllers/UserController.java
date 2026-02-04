package com.scm.scm20.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm20.entities.User;
import com.scm.scm20.services.ContactService;
import com.scm.scm20.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    // ==========================
    // USER DASHBOARD
    // ==========================
    @RequestMapping("/dashboard")
    public String userDashboard(Model model, Authentication authentication) {

        User loggedInUser = resolveLoggedInUser(authentication);

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        long totalContacts =
                contactService.countByUser(loggedInUser);
        long favoriteContacts =
                contactService.countFavoritesByUser(loggedInUser);

        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("totalContacts", totalContacts);
        model.addAttribute("favoriteContacts", favoriteContacts);

        return "user/dashboard";
    }

    // ==========================
    // USER PROFILE
    // ==========================
    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {

        User loggedInUser = resolveLoggedInUser(authentication);

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("loggedInUser", loggedInUser);
        return "user/profile";
    }

    // ==========================
    // 🔥 CENTRAL FIX METHOD
    // ==========================
    private User resolveLoggedInUser(Authentication authentication) {

        if (authentication == null) {
            return null;
        }

        // NORMAL LOGIN
        if (!(authentication instanceof OAuth2AuthenticationToken)) {
            String email = authentication.getName();
            return userService.getUserByEmail(email);
        }

        // GOOGLE LOGIN
        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;
        OAuth2User oauthUser = oauthToken.getPrincipal();

        String email = oauthUser.getAttribute("email");
        return userService.getUserByEmail(email);
    }
    @RequestMapping("/profile/update")
    public String updateProfile(
            Authentication authentication,
            String about,
            String phoneNumber,
            String profilePic
    ) {

        User loggedInUser = resolveLoggedInUser(authentication);

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        userService.updateProfile(
                loggedInUser.getUserId(),
                about,
                phoneNumber,
                profilePic
        );

        return "redirect:/user/profile";
    }

}
