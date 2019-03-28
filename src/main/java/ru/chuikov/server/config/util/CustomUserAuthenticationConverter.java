package ru.chuikov.server.config.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;
import ru.chuikov.server.entity.UserPrincipal;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomUserAuthenticationConverter implements UserAuthenticationConverter {


    private final String email="email";
    private final String id="id";
    private final String firstName="firstName";
    private final String lastName="lastName";
    private final String account_enabled="account_enabled";

    private final String account_locked="account_locked";
    private final String account_expired="account_expired";
    private final String credentials_expired="credentials_expired";
    private final String roles="roles";


    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put(USERNAME, userAuthentication.getName());

        if (userAuthentication.getAuthorities() != null && !userAuthentication.getAuthorities().isEmpty())
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(userAuthentication.getAuthorities()));

        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(USERNAME)) {
            UserPrincipal user = new UserPrincipal(map);

            return new UsernamePasswordAuthenticationToken(
                user, "N/A",
                getAuthorities(map));
    }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {

        Object authorities = map.get(AUTHORITIES);

        if (authorities instanceof String)
            return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);

        if (authorities instanceof Collection)
            return AuthorityUtils.commaSeparatedStringToAuthorityList(
                    StringUtils.collectionToCommaDelimitedString((Collection<?>) authorities));

        throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }

}
