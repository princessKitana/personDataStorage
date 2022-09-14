package com.pds.core.service.person.get;

import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.person.PersonRequest;
import com.pds.core.service.validation.AbstractPersonValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetPersonValidator extends AbstractPersonValidator {

    public List<ApplicationError> validate(PersonRequest request) {
        List<ApplicationError> errors = new ArrayList<>();
        checkIsValidPersonalId(request.getPersonalId()).ifPresent(errors::add);
        checkDateOfBirthPattern(request.getDateOfBirth()).ifPresent(errors::add);
        return errors;
    }

}


