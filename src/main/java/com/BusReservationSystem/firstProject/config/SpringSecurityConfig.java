package com.BusReservationSystem.firstProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.BusReservationSystem.firstProject.service.DefaultUserServiceImpl;
import com.BusReservationSystem.firstProject.config.CustomSuccessHandler;


@SuppressWarnings("deprecation")
@Configuration

public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    private DefaultUserServiceImpl customUserDetailsService;


    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customUserDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(customUserDetailsService);
//        auth.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // No encoding
//        return auth;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/registration").permitAll().anyRequest()
                .authenticated().and().formLogin().loginPage("/login").successHandler(successHandler).
                permitAll().and().logout()
                .invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
                logoutSuccessUrl("/login?logout")
                .permitAll();

    }
}
