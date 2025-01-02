package com.firstproject.demo.service;

import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String email, String mailBody, String subject) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
        mimeMessageHelper.setFrom("tset45978t@gmail.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(mailBody, true);
        javaMailSender.send(mimeMessageHelper.getMimeMessage());
        System.out.println("Mail sent successfully");
    }
}
