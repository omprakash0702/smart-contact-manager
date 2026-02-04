package com.scm.scm20.services.impl;

import org.springframework.stereotype.Service;
import com.scm.scm20.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("EMAIL SKIPPED -> " + to);
    }

    @Override
    public void sendEmailWithHtml() {
    }

    @Override
    public void sendEmailWithAttachment() {
    }
}
