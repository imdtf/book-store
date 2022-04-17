package com.github.imdtf.bookstore.domain.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/15 22:38
 * 4
 */
@Service
public class JwtAccessTokenService extends DefaultTokenServices {

    @Inject
    public JwtAccessTokenService(JwtAccessToken token, AuthClientDetailsServiceImpl clientDetailsService, AuthenticationManager authenticationManager) {
        setTokenStore(new JwtTokenStore(token));

        setClientDetailsService(clientDetailsService);

        setAuthenticationManager(authenticationManager);

        setTokenEnhancer(token);

        setAccessTokenValiditySeconds(60 * 60 * 3);

        setRefreshTokenValiditySeconds(60 * 60 * 24 * 15);

        setSupportRefreshToken(true);

        setReuseRefreshToken(true);
    }
}
