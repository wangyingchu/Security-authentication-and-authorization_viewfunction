package com.viewfunction.securityAuthenticationAndAuthorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.Collections;

@Configuration
public class AuthenticationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //disable password encode
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Value("${userDnPatterns}")
    private String userDnPatterns;
    @Value("${userSearchBase}")
    private String userSearchBase;
    @Value("${ldapConnectionAddress}")
    private String ldapConnectionAddress;
    @Value("${ldapBaseDn}")
    private String ldapBaseDn;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        //inMemoryAuthentication 和 ldapAuthentication 中设定的用户认证信息可同时生效
        //初始化逻辑........设定user 认证信息
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("USER", "ADMIN");

        //初始化逻辑........设定 LDAP user 认证信息
        auth.ldapAuthentication()
                .userDnPatterns(userDnPatterns)
                .userSearchBase(userSearchBase)
                //.userSearchFilter("......")
                //.groupSearchBase("......")
                //.groupSearchFilter("......")
                .contextSource(contextSource());
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        DefaultSpringSecurityContextSource defaultSpringSecurityContextSource=
                new DefaultSpringSecurityContextSource(Collections.singletonList(ldapConnectionAddress), ldapBaseDn);
        return defaultSpringSecurityContextSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated()
               .and().httpBasic().and().csrf().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}