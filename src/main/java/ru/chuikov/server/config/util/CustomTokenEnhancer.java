package ru.chuikov.server.config.util;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import ru.chuikov.server.entity.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

        info.put("email",user.getEmail());
        info.put("id",user.getId());
        info.put("firstName",user.getFirstName());
        info.put("lastName",user.getLastName());
        info.put("account_enabled",user.isEnabled());
        info.put("account_locked",user.isAccountNonLocked());
        info.put("account_expired",user.isAccountNonExpired());
        info.put("credentials_expired",user.isCredentialsNonExpired());
        info.put("roles",user.getRoles());

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);

        return super.enhance(customAccessToken, authentication);
    }
}
