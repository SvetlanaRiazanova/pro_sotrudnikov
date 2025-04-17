package di.aittr.pro_sotrudnikov.security;

import di.aittr.pro_sotrudnikov.domen.entity.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class AuthInfo implements Authentication {

    private boolean authenticated;
    private String username;
    private List<Role> roles;

    public AuthInfo(String username, List<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthInfo authInfo = (AuthInfo) o;
        return authenticated == authInfo.authenticated && Objects.equals(username, authInfo.username) && Objects.equals(roles, authInfo.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authenticated, username, roles);
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("Auth info: авторизован - %b, имя - %s, роли - %s.", authenticated, username, roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;

    }
}
