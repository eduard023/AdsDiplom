package ru.skypro.homework.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserPrincipalDto;

import java.util.Collection;
import java.util.List;

public class UserPrincipalDetailsImpl implements UserDetails {

    private UserPrincipalDto userPrincipalDto;

    public UserPrincipalDetailsImpl(UserPrincipalDto userPrincipalDto) {
        this.userPrincipalDto = userPrincipalDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + userPrincipalDto.getRole().name());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return userPrincipalDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userPrincipalDto.getUsername();
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
