package com.pds.core.service.validation;


import com.pds.core.enums.BusinessRules;
import com.pds.core.repository.UserRepository;
import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.error.ErrorUtil;
import com.pds.core.service.user.AddUserRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractUserValidator {

    @Autowired
    private UserRepository userRepository;

    public abstract List<ApplicationError> validate(AddUserRequest request);

//    public Optional<ApplicationError> checkUsernamePresentInDb(String username) {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (!user.isPresent()) {
//            return Optional.empty();
//        } else {
//            return ErrorUtil.createApplicationValidationError("Username", BusinessRules.BR_3007);
//        }
//    }

    public Optional<ApplicationError> checkFieldIsPresent(String propertyValue, String propertyName) {
        if (StringUtils.isEmpty(propertyValue)) {
            return ErrorUtil.createApplicationValidationError(propertyName, BusinessRules.BR_3000);
        } else
            return Optional.empty();
    }

}
