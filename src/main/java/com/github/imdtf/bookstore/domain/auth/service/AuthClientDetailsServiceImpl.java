package com.github.imdtf.bookstore.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/15 22:44
 * 4
 */
@Service
@RequiredArgsConstructor
public class AuthClientDetailsServiceImpl implements ClientDetailsService {

    private static final String CLIENT_ID = "bookstore_frontend";

    private static final String CLIENT_SECRET = "bookstore_secret";

    private final PasswordEncoder passwordEncoder;

    private ClientDetailsService clientDetailsService;

    @PostConstruct
    public void init() throws Exception {
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
        builder.withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .scopes("BROWSER")
                .authorizedGrantTypes("password", "refresh_token");
        clientDetailsService = builder.build();
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(clientId);
    }
}
