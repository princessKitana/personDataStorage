package com.pds.core.service.person.add;

import com.pds.core.service.person.PersonRequest;
import com.pds.core.service.person.PersonResponse;


public interface AddPersonService {

    PersonResponse addPerson(PersonRequest request);

}
