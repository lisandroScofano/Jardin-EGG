/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldenkids.springboot.web.app;

import com.goldenkids.springboot.web.app.models.TipoPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.goldenkids.springboot.web.app.services.JpaUserSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author lisandroscofano
 */
@Configuration
public class SpringSecurityConfiguracion extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserSecurityService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**","/login", "/css/**", "/js/**", "/images/**", "/usuario/**").permitAll()//a estas paginas accede cualquier persona sin loguearse
                //.antMatchers("/salita/**", "/alumno/**").hasAnyRole(TipoPerfil.DIRECTIVO.toString())// aca restringimos el acceso segun el rol
//                .antMatchers("/usuario/guardar").permitAll() // aca restringimos el acceso segun el rol
//                .antMatchers("/alumno/**").hasAnyRole(TipoPerfil.DIRECTIVO.toString())
//                .antMatchers("/salita/**").hasAnyRole(TipoPerfil.DIRECTIVO.toString())
//                .antMatchers("/autorizados/**").hasAnyRole(TipoPerfil.DIRECTIVO.toString())
               // .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        PasswordEncoder encoder = passwordEncoder();
        
        UserBuilder users = User.builder().passwordEncoder(encoder::encode);

        builder.inMemoryAuthentication()
                .withUser(users.username("lisandros").password("1234").authorities("DIRECTIVO"));

    }
}
