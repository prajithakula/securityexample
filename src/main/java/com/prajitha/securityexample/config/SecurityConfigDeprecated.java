package com.prajitha.securityexample.config;

import com.prajitha.securityexample.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//override configure method with @EnableWebSecurity annotation
//@EnableWebSecurity
//public class SecurityConfigDeprecated extends WebSecurityConfigurerAdapter {
////    private final AuthenticationService authenticationService;
////    private final JwtRequestFilter jwtRequestFilter;
////
////    public SecurityConfigDeprecated(AuthenticationService authenticationService,
////                                    JwtRequestFilter jwtRequestFilter) {
////        this.authenticationService = authenticationService;
////        this.jwtRequestFilter = jwtRequestFilter;
////    }
//
////    @Override
////    //create what users are authenticate to access the service
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////       auth.userDetailsService(authenticationService);
////    }
////
////    @Override
////    //By pass authorization for resources
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable().authorizeRequests().antMatchers("/auth/*").permitAll() //Allow ALl requests tom/auth path
////                .anyRequest().authenticated() //Any Other requests should be authenticated
////                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Setting Stateless Policy
////
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); //Adding our filter before UserNamePasswordAuthenticationFilter
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return NoOpPasswordEncoder.getInstance();
////    }
////
////    //Defining Bean for Authorization Manager
////    @Bean
////    public AuthenticationManager getAuthorizationManager() throws Exception {
////        return super.authenticationManagerBean();
//
//
//    //}
//
//}
