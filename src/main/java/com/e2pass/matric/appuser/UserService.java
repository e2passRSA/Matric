package com.e2pass.matric.appuser;

import com.e2pass.matric.registration.RegistrationErrors;
import com.e2pass.matric.shared.ServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final String USER_NOT_FOUND = "user with email %s not found";
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND, username)
                )
        );
    }

    public ServiceResponse<User> registerNewUser(User user)
    {
        var userExist = userRepository.findByEmail(user.getEmail()).isPresent();

        if(!userExist)
        {
            var response = userRepository.save(user);
            return new ServiceResponse<>(true, response, LocalDateTime.now(), new ArrayList<>());
        }
        var errors = new ArrayList<String>();
        errors.add(RegistrationErrors.CONFLICT.name());

        return new ServiceResponse<>(false,
                LocalDateTime.now(), errors);

    }
}
