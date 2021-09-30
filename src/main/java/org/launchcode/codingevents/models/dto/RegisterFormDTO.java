package org.launchcode.codingevents.models.dto;

// 19.4.3 Form DTOs

public class RegisterFormDTO extends LoginFormDTO {

    private String verifyPassword;

    public String getVerifyPassword() { return verifyPassword; }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }


}
