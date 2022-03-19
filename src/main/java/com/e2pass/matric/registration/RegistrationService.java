package com.e2pass.matric.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService implements IRegistration {

    public String registerUser(RegistrationRequest request){

        //TODO: check the email for validation, create a user and send it to userService.
        
        return "It works!";
    }
}
