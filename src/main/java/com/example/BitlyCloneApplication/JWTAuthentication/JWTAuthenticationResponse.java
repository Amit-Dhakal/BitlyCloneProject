package com.example.BitlyCloneApplication.JWTAuthentication;

import lombok.Data;
import java.util.Date;

@Data
public class JWTAuthenticationResponse {

private String token;
private String Users;
private Date expiration;
    private Date IssuedAT;
    private String Roles;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getUsers() {
        return Users;
    }

    public void setUsers(String users) {
        Users = users;
    }

    public Date getIssuedAT() {
        return IssuedAT;
    }

    public void setIssuedAT(Date issuedAT) {
        IssuedAT = issuedAT;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }
}
