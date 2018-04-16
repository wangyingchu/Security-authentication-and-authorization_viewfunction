package com.viewfunction.securityAuthenticationAndAuthorization;

import com.viewfunction.securityAuthenticationAndAuthorization.util.ApplicationLauncherUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class SecurityAuthenticationAndAuthorizationApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SecurityAuthenticationAndAuthorizationApplication.class, args);
        ApplicationLauncherUtil.printApplicationConsoleBanner();
	}
}
