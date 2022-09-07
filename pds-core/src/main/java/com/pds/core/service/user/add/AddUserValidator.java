package com.pds.core.service.user.add;

import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.user.AddUserRequest;

import com.pds.core.service.validation.AbstractUserValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddUserValidator extends AbstractUserValidator {

    public List<ApplicationError> validate(AddUserRequest request) {
        List<ApplicationError> errors = new ArrayList<>();
        checkFieldIsPresent(request.getPersonalId(), "personalId").ifPresent(errors::add);
       // checkFieldIsPresent(request.getPassword(), "Password").ifPresent(errors::add);
        //checkUsernamePresentInDb(request.getUsername()).ifPresent(errors::add);
        return errors;
    }

}


