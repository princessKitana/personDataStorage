package com.pds.core.service.person.get;

import com.pds.core.domain.Person;
import com.pds.core.service.person.PersonRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetPersonService{

    Page<Person> getAllPersons(Integer pageNo, Integer pageSize);

    List<Person> findPersonsByParams(PersonRequest request);
}
