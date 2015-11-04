package com.realdolmen.fleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                .usersByUsernameQuery("select email, password, true from Employee where email=?")
                .authoritiesByUsernameQuery("select email, role from Employee where email=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin()
                .loginPage("/login").defaultSuccessUrl("/")
                .usernameParameter("email").passwordParameter("password")
                .and().rememberMe().tokenValiditySeconds(2419200)
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and().authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/**").hasRole("NORMAL")
                .antMatchers("/**").hasRole("FLEET")
                .antMatchers("/fleet/**").hasRole("FLEET");

    }

    //TODO REMOVE
    public static void main(String... args){
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        System.out.println(e.encode("123"));
        System.out.println(e.encode("123"));
        System.out.println(e.matches("123", "$2a$10$DrWVeZWQVSiyc5hZXoD5Z.vYB9zsCcIcn4jrilfbkPUiXwFozVGk6"));
        System.out.println(e.matches("123", "$2a$10$kTX.dLlc/TZB4NzmpHpS5ei.THZYkROs/Ri1/TzcjxwswKjwm/3QS"));
        System.out.println(e.encode("123"));
        System.out.println(e.encode("123"));
        System.out.println(e.encode("123"));
        System.out.println(e.encode("password"));
        System.out.println(0 % 3);
    }

}