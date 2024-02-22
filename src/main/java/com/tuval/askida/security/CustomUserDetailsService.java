package com.tuval.askida.security;

import com.tuval.askida.model.Owner;
import com.tuval.askida.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner user = userService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Girilen email adresi ile kullanıcı bulunamadı: " + username));
        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword());
    }
}
