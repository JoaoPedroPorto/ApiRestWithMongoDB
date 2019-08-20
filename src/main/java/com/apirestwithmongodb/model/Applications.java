package com.apirestwithmongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Document
public class Applications implements UserDetails {

    private String login;
    private String pw;

    // GETTERS AND SETTERS

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPw() { return pw; }

    public void setPw(String pw) { this.pw = pw; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return null; }

    @Override
    public String getPassword() { return this.pw; }

    @Override
    public String getUsername() { return this.login; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

}
