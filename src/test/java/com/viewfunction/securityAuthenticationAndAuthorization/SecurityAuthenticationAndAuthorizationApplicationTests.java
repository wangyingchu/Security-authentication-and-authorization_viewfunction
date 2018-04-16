package com.viewfunction.securityAuthenticationAndAuthorization;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityAuthenticationAndAuthorizationApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testOAuthService() {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setUsername("user");
        resource.setPassword("password");

        resource.setAccessTokenUri("http://localhost:12000/oauth/token");
        resource.setClientId("client2");
        resource.setClientSecret("secret2");
        resource.setGrantType("password");
        //resource.setScope(Arrays.asList(new String[]{"read","write","trust"}));
		resource.setScope(Arrays.asList(new String[]{"app2"}));

		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);
		String greet = restTemplate.getForObject("http://www.baidu.com", String.class);
		System.out.println(greet);
        System.out.println(greet);
        System.out.println(greet);
	}

}
