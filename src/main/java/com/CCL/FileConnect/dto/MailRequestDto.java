package com.CCL.FileConnect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MailRequestDto {
    @Email(message = "Valid email is required.")
    private String mail;

    @NotBlank(message = "Url is required.")
    private String url;

    public MailRequestDto() {
    }

    public MailRequestDto(String mail, String url) {
        this.mail = mail;
        this.url = url;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
