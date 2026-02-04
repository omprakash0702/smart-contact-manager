package com.scm.scm20.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserExtraController {

    @GetMapping("/messages")
    public String messages() {
        return "user/messages";
    }

    @GetMapping("/feedback")
    public String feedback() {
        return "user/feedback";
    }

    @GetMapping("/profile/edit")
    public String editProfile() {
        return "user/edit_profile";
    }

    @GetMapping("/contacts/export")
    public void exportContacts(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=contacts.csv");
        response.getWriter().write("Name,Email,Phone\n");
    }
}

