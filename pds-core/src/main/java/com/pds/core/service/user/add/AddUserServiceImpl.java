package com.pds.core.service.user.add;


import com.pds.core.domain.User;
import com.pds.core.enums.Gender;
import com.pds.core.repository.UserRepository;
import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.error.ApplicationException;
import com.pds.core.service.user.AddUserRequest;
import com.pds.core.service.user.AddUserResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AddUserServiceImpl implements AddUserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddUserValidator validator;

    @Override
    @Transactional
    public AddUserResponse register(AddUserRequest request) {
        log.info("Adding user");

        List<ApplicationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            throw new ApplicationException(validationErrors);
        }

        User user = new User();
        user.setPersonalId(request.getPersonalId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());

        userRepository.saveAndFlush(user);

        AddUserResponse response = new AddUserResponse();
        response.setPersonalId(user.getPersonalId());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setGender(Gender.valueOf(user.getGender()));

        return response;
    }

}
