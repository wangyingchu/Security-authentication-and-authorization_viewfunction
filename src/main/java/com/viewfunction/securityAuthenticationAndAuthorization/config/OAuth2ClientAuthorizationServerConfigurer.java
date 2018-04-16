package com.viewfunction.securityAuthenticationAndAuthorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@ComponentScan
public class OAuth2ClientAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    //此处自动链接的是 AuthenticationSecurityConfiguration
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        endpoints.authenticationManager(authenticationManager);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //初始化逻辑........设定client 授权信息
        clients.inMemory() // 使用in-memory存储
                .withClient("client") // client_id
                .secret("secret") // client_secret
                .authorizedGrantTypes("authorization_code","refresh_token", "password", "client_credentials") // 该client允许的授权类型
                .scopes("app")//; // 允许的授权范围
        .and()
                .withClient("client2") // client_id
                .secret("secret2") // client_secret
                .authorizedGrantTypes("authorization_code","refresh_token", "password", "client_credentials") // 该client允许的授权类型
                .scopes("app2","app3"); // 允许的授权范围
    }
}
