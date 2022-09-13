package com.pds.core.service.person.add;

import com.pds.core.domain.Person;
import com.pds.core.enums.Gender;
import com.pds.core.repository.PersonRepository;
import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.error.ApplicationException;
import com.pds.core.service.log.LogRequest;
import com.pds.core.service.log.add.AddLogService;
import com.pds.core.service.person.PersonRequest;
import com.pds.core.service.person.PersonResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class AddPersonServiceImpl implements AddPersonService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddLogService logService;

    @Autowired
    private AddPersonValidator validator;

    @Override
    @Transactional
    public PersonResponse addPerson(PersonRequest request) {
        List<ApplicationError> validationErrors = validator.validate(request);
        if (!validationErrors.isEmpty()) {
            throw new ApplicationException(validationErrors);
        }

        Person person = new Person();
        person.setPersonalId(request.getPersonalId());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        person.setGender(request.getGender().name());

        personRepository.saveAndFlush(person);

        PersonResponse response = new PersonResponse();
        response.setPersonalId(person.getPersonalId());
        response.setDateOfBirth(String.valueOf(person.getDateOfBirth()));
        response.setFirstName(person.getFirstName());
        response.setLastName(person.getLastName());
        response.setGender(Gender.valueOf(person.getGender()));

        logAction(person);

        return response;
    }

    private void logAction(Person person) {
        var logRequest = new LogRequest();
        logRequest.setTimestamp(LocalDateTime.now());
        logRequest.setLogData(Objects.toString(person));
        logRequest.setName("Person data saved in db");
        var logResponse = logService.addLog(logRequest);
        logger.info("Person data saved in db, log id:" + logResponse.getId());
    }

}
