package com.pds.core.service.validation;


import com.pds.core.domain.Person;
import com.pds.core.enums.BusinessRules;
import com.pds.core.repository.PersonRepository;
import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.error.ErrorUtil;
import com.pds.core.service.person.PersonRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public abstract class AbstractPersonValidator {

    @Autowired
    private PersonRepository personRepository;

    public abstract List<ApplicationError> validate(PersonRequest request);

    public Optional<ApplicationError> checkFieldIsPresent(String propertyValue, String propertyName) {
        if (StringUtils.isBlank(propertyValue)) {
            return ErrorUtil.createApplicationValidationError(propertyName, BusinessRules.BR_3000);
        } else
            return Optional.empty();
    }

    public Optional<ApplicationError> checkDateOfBirth(String date) {
        if (StringUtils.isBlank(date)) {
            return ErrorUtil.createApplicationValidationError("dateOfBirth", BusinessRules.BR_3002);
        }

        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!Pattern.matches(pattern, date)) {
            return ErrorUtil.createApplicationValidationError("dateOfBirth", BusinessRules.BR_3003);
        }

        try {
            var parsedDate = LocalDate.parse(date);
            if (parsedDate.isAfter(LocalDate.now())) {
                return ErrorUtil.createApplicationValidationError("dateOfBirth", BusinessRules.BR_3002);
            }
        } catch (Exception e) {
            return ErrorUtil.createApplicationValidationError("dateOfBirth", BusinessRules.BR_3003);
        }

        return Optional.empty();
    }

    public Optional<ApplicationError> checkIsValidPersonalId(String personalId) {
        String pattern = "^([0-9]{6})(-{0,1})([0-9]{5})$";
        if (personalId != null && Pattern.matches(pattern, personalId)) {
            return Optional.empty();
        } else {
            return ErrorUtil.createApplicationValidationError("personalId", BusinessRules.BR_3001);
        }
    }

    public Optional<ApplicationError> checkPersonalIdPresentInDb(String personalId) {
        Optional<Person> user = personRepository.findByPersonalId(personalId);
        if (user.isEmpty()) {
            return Optional.empty();
        } else {
            return ErrorUtil.createApplicationValidationError("personalId", BusinessRules.BR_3004);
        }
    }

}
