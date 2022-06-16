package com.zorba.shoppingCart.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("springuser")
                .password(passwordEncoder().encode("spring123"))
                .roles("USER")
                .and()
                .withUser("springadmin").password(passwordEncoder().encode("admin123"))
                .roles("ADMIN");


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/shoppingCart/saveRecords")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/shoppingCart/fetchRecords")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE,"/shoppingCart/deleteRecords")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();

    }
}
