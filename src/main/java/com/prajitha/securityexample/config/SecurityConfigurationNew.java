package com.prajitha.securityexample.config;

import com.prajitha.securityexample.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationNew {

    private final AuthenticationService authenticationService;
    private final JwtRequestFilter jwtRequestFilter;

    //inject the required services to get the instance eg -> jwtRequestFilter and authenticationService
    public SecurityConfigurationNew(AuthenticationService authenticationService,
                                    JwtRequestFilter jwtRequestFilter) {
        this.authenticationService = authenticationService;
        this.jwtRequestFilter = jwtRequestFilter;
    }
    @Bean
    //Adding Authorization
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable() // enable the cors
                .authorizeRequests()
                .antMatchers ("/auth/*").permitAll() //allow the authenticate requests to the end points
                .anyRequest().authenticated().and().sessionManagement()  // enable the stateless URI
                .sessionCreationPolicy (SessionCreationPolicy. STATELESS); //Setting Stateless Policy
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); //Adding our filter before UserNamePasswordAuthenticationFilter
        return http.build();
    }
    @Bean
    //Adding password encoder to create the encoded password string
    public PasswordEncoder passwordEncoder(){
       return NoOpPasswordEncoder.getInstance();
    }
    //Defining Bean for Authorization Manager
    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
