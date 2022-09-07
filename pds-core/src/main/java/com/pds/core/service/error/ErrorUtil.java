package com.pds.core.service.error;

import com.pds.core.enums.BusinessRules;
import com.pds.core.enums.Constants;

import java.util.Optional;

public class ErrorUtil {

    private ErrorUtil() {
        throw new IllegalStateException("ErrorUtil class");
    }

    public static Optional<ApplicationError> createApplicationValidationError(String field, BusinessRules businessRule){
        ApplicationError error = new ApplicationError();
        error.setField(field);
        error.setStatusCode(businessRule.name());
        error.setDescription(field + ": " + businessRule.getMessage());
        error.setType(Constants.VALIDATION_ERROR);
        error.setSeverity(Constants.VALIDATION);
        return Optional.of(error);
    }

}
