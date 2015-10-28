package com.realdolmen.fleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Created on 28/10/2015.
 *
 * @author Robin D'Haese
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from author where username=?")
                .authoritiesByUsernameQuery("select username, role from author where username=?")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

        //For in memory user store:
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("ROLE_USER").and().withUser("admin").password("password").roles("ROLE_USER", "ROLE_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .and().rememberMe().tokenValiditySeconds(2419200)
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and().authorizeRequests()
                .antMatchers("/fleet/**").hasRole("FLEET")
                .antMatchers("/employee/**").hasRole("NORMAL")
                .antMatchers("/employee/**").hasRole("FLEET")
                .anyRequest().permitAll();
    }

}
