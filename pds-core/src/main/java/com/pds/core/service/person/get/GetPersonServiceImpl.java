package com.pds.core.service.person.get;

import com.pds.core.domain.Person;
import com.pds.core.enums.Gender;
import com.pds.core.repository.PersonRepository;
import com.pds.core.service.person.PersonRequest;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class GetPersonServiceImpl implements GetPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Page<Person> getAllPersons(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return personRepository.findAll(paging);
    }

    @Override
    public List<Person> findPersonsByParams(PersonRequest request) {
        String firstName = StringUtils.isNotBlank(request.getFirstName()) ? request.getFirstName() : null;
        String lastName = StringUtils.isNotBlank(request.getLastName()) ? request.getLastName() : null;
        String personalId = StringUtils.isNotBlank(request.getPersonalId()) ? request.getPersonalId() : null;
        LocalDate dateOfBirth = StringUtils.isNotBlank(request.getDateOfBirth()) ? LocalDate.parse(request.getDateOfBirth()) : null;

        String gender = null;
        if (request.getGender() != null) {
            gender = String.valueOf(request.getGender());
        }

        return personRepository.findByParameters(firstName,
                lastName,
                personalId,
                gender,
                dateOfBirth);
    }

}
