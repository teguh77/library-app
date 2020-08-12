package io.tamknown.libapp.auth;

import io.tamknown.libapp.model.User;
import io.tamknown.libapp.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Set;


public class ApplicationUser implements UserDetails {

    private PasswordEncoder passwordEncoder;

    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> authorities;

    public ApplicationUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ApplicationUser(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = ApplicationUserRole
                .valueOf(user.getRoles())
                .getGrantedAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
