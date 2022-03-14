package com.e2pass.matric.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final String USER_NOT_FOUND = "user with email %s not found";
    private final IUserRepository appRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND, username)
                )
        );
    }
}
