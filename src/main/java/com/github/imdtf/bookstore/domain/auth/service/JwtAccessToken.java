package com.github.imdtf.bookstore.domain.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/15 22:39
 * 4
 */
@Component
public class JwtAccessToken extends JwtAccessTokenConverter {

    private static final String JWT_TOKEN_STRING_PRIVATE_KEY = "601304E0-8AD4-40B0-BD51-0B432DC47461";

    @Inject
    public JwtAccessToken(UserDetailsService userDetailsService) {
        setSigningKey(JWT_TOKEN_STRING_PRIVATE_KEY);
        DefaultUserAuthenticationConverter converter = new DefaultUserAuthenticationConverter();
        converter.setUserDetailsService(userDetailsService);
        ((DefaultAccessTokenConverter) getAccessTokenConverter()).setUserTokenConverter(converter);
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Authentication user = authentication.getUserAuthentication();
        String[] authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
        Map<String, Object> payLoad = new HashMap<>(2);
        payLoad.put("username", user.getName());
        payLoad.put("authority", authorities);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(payLoad);
        return super.enhance(accessToken, authentication);
    }
}
