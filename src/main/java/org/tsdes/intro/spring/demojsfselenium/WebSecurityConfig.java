package org.tsdes.intro.spring.demojsfselenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) {
        try {
            /*
                JSF has its own CSRF Token mechanism based on JSF-Views.
                So, we just disable the Spring Security one.
             */
            http.csrf().disable();

            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/index.*").permitAll()
                    .antMatchers("/signup.*").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    /*
                        Login page config
                     */
                    .formLogin()
                    .loginPage("/login.jsf")
                    .permitAll()
                    .failureUrl("/login.jsf?error=true")
                    .defaultSuccessUrl("/index.jsf?faces-redirect=true")
                    .and()
                    /*
                        Spring Security will automatically create an endpoint
                        which handles POST on /logout.
                      */
                    .logout()
                    .logoutSuccessUrl("/index.jsf?faces-redirect=true");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery(
                            "SELECT username, password, TRUE AS enabled " +
                                    "FROM user_entity " +
                                    "WHERE username = ?"
                    )
                    .authoritiesByUsernameQuery(
                            "SELECT x.username, 'USER' AS roles " +
                                    "FROM user_entity x " +
                                    "WHERE x.username = ? "
                    )
                    .passwordEncoder(passwordEncoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
