package com.cherrymarket.utility;

import com.cherrymarket.entity.AuthorityEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AuthorityUtility {

    public static final String ADMINISTRATOR = "ADMINISTRATOR";
    public static final String VIEW_USER_CREDENTIALS = "VIEW_USER_CREDENTIALS";

    private AuthorityUtility() {}

    public static Collection<? extends GrantedAuthority> mapToGrantedAuthorities(Set<AuthorityEntity> authorities) {
        return authorities.stream().map(a -> new SimpleGrantedAuthority(a.getName())).toList();
    }

    public static List<String> mapToStringList(Set<AuthorityEntity> authorities) {
        return authorities.stream().map(AuthorityEntity::getName).toList();
    }

    public static List<String> mapToStringList(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).toList();
    }

    public static boolean isAdministrator(Collection<? extends GrantedAuthority> authorities) {
        return mapToStringList(authorities).contains(ADMINISTRATOR);
    }

    public static boolean canViewUserCredentials(Collection<? extends GrantedAuthority> authorities) {
        return mapToStringList(authorities).contains(VIEW_USER_CREDENTIALS);
    }

}
