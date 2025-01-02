package com.firstproject.demo.dto;



public class MailBodyDto {
    private String email;
    private String subject;
    private String mailBody;

    public MailBodyDto() {
    }

    public MailBodyDto(String email, String subject, String mailBody) {
        this.email = email;
        this.subject = subject;
        this.mailBody = mailBody;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }
}
