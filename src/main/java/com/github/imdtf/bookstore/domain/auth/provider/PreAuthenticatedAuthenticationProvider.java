package com.github.imdtf.bookstore.domain.auth.provider;

import com.github.imdtf.bookstore.domain.auth.AuthenticAccount;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 1 * @Author: deng.tengfei
 * 2 * @email: imdtf@qq.com
 * 3 * @Date: 2022/4/15 23:06
 * 4
 */
@Component
public class PreAuthenticatedAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getPrincipal() instanceof UsernamePasswordAuthenticationToken) {
            AuthenticAccount user = (AuthenticAccount) ((UsernamePasswordAuthenticationToken) authentication.getPrincipal()).getPrincipal();
            if (user.isEnabled() && user.isCredentialsNonExpired() && user.isAccountNonExpired() && user.isAccountNonLocked()) {
                return new PreAuthenticatedAuthenticationToken(user, "", user.getAuthorities());
            } else {
                throw new DisabledException("user status is not correct");
            }
        } else {
            throw new PreAuthenticatedCredentialsNotFoundException("预验证失败，传上来的令牌是怎么来的？");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
