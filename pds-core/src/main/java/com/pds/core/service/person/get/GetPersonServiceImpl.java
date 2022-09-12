package com.pds.core.service.person.get;

import com.pds.core.domain.Person;
import com.pds.core.repository.PersonRepository;
import com.pds.core.service.person.PersonRequest;
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
        LocalDate dateOfBirth = null;
        if(request.getDateOfBirth()!=null){
            dateOfBirth = LocalDate.parse(request.getDateOfBirth());
        }

        String gender = null;
        if(request.getGender()!=null){
            gender = request.getGender().name();
        }

        //todo validate req
        return personRepository.findByParameters(request.getFirstName(),
                request.getLastName(),
                request.getPersonalId(),
                gender,
                dateOfBirth);

    }

}
