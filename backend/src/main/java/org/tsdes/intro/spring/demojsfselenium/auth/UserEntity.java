package org.tsdes.intro.spring.demojsfselenium.auth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
@Entity
public class UserEntity {

    @Id
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UserEntity() {
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
