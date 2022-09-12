package com.pds.core.service.person.add;

import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.person.PersonRequest;

import com.pds.core.service.validation.AbstractPersonValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddPersonValidator extends AbstractPersonValidator {

    public List<ApplicationError> validate(PersonRequest request) {
        List<ApplicationError> errors = new ArrayList<>();
        checkFieldIsPresent(request.getPersonalId(), "personalId").ifPresent(errors::add);
        checkFieldIsPresent(request.getDateOfBirth(), "dateOfBirth").ifPresent(errors::add);
        if (errors.isEmpty()) {
            checkIsValidPersonalId(request.getPersonalId()).ifPresent(errors::add);
            checkDateOfBirth(request.getDateOfBirth()).ifPresent(errors::add);
        }
        checkPersonalIdPresentInDb(request.getPersonalId()).ifPresent(errors::add);
        return errors;
    }

}


