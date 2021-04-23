package org.tsdes.intro.spring.demojsfselenium.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
@Named
@RequestScoped
public class SignUpController {

    @Autowired
    private UserService userService;

    private String username;

    private String password;

    public String signUpUser(){

        boolean registered = false;
        try {
            registered = userService.createUser(username, password);
        } catch (Exception e){
            //nothing to do
        }

        if(registered){
            return "/login.jsf?faces-redirect=true";
        } else {
            return "/signup.jsf?faces-redirect=true&error=true";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
