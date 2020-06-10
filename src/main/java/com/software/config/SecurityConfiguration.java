package com.software.config;

import java.io.PrintWriter;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.auth.AccessDeniedHandlerImpl;
import com.software.auth.AuthenticationFailureHandlerImpl;
import com.software.auth.AuthenticationSuccessHandlerImpl;
import com.software.auth.LoginAuthenticationFilter;
import com.software.auth.RestAuthenticationEntryPoint;
import com.software.auth.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Referemce: https://medium.com/%E4%BC%81%E9%B5%9D%E4%B9%9F%E6%87%82%E7%A8%8B%E5%BC%8F%E8%A8%AD%E8%A8%88/spring-security-%E7%B5%90%E5%90%88restfulapi%E7%9A%84%E8%A8%AD%E8%A8%88-60b778fd3b22
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .httpBasic();
        // http.exceptionHandling()
        //         .authenticationEntryPoint(new RestAuthenticationEntryPoint())
        //         .accessDeniedHandler(new AccessDeniedHandlerImpl())
        //         .and()
        //         .addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        //         .authorizeRequests()
        //         .anyRequest().authenticated()
        //         .antMatchers("/api/admin/**").hasRole("ADMIN")
        //         .antMatchers("/api/user/**").hasRole("USER")
        //         .and()
        //         .logout()
        //         .logoutUrl("/api/logout")
        //         .invalidateHttpSession(true)
        //         .logoutSuccessHandler((req, resp, auth) -> {
        //             resp.setContentType("application/json;charset=UTF-8");
        //             PrintWriter out = resp.getWriter();
        //             resp.setStatus(200);
        //             Map<String, String> result = Map.of("message", "Logout success");
        //             ObjectMapper om = new ObjectMapper();
        //             out.write(om.writeValueAsString(result));
        //             out.flush();
        //             out.close();
        //         })
        //         .and()
        //         .csrf()
        //         .disable();
    }

    @Bean
    LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandlerImpl());
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandlerImpl());
        filter.setFilterProcessesUrl("/api/login");
        return filter;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}