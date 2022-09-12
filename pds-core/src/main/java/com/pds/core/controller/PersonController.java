package com.pds.core.controller;


import com.pds.core.api.common.v1.AddPersonDTO;
import com.pds.core.api.common.v1.GetPersonDTO;
import com.pds.core.api.common.v1.PersonDTO;
import com.pds.core.domain.Person;
import com.pds.core.enums.Gender;
import com.pds.core.service.log.LogRequest;
import com.pds.core.service.log.add.AddLogService;
import com.pds.core.service.person.PersonRequest;
import com.pds.core.service.person.PersonResponse;
import com.pds.core.service.person.add.AddPersonService;
import com.pds.core.service.person.get.GetPersonService;
import com.pds.core.util.Util;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.pds.core.util.Util.getRandomIntNumber;

@RestController
@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private AddPersonService addPersonService;

    @Autowired
    private AddLogService logService;

    @Autowired
    private GetPersonService getPersonService;

    @PostMapping(value = "/addPerson")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody AddPersonDTO personDTO) {
        PersonRequest addPersonRequest = new PersonRequest();
        addPersonRequest.setPersonalId(personDTO.getPersonalId());
        addPersonRequest.setDateOfBirth(personDTO.getDateOfBirth());
        addPersonRequest.setFirstName("John" + getRandomIntNumber());
        addPersonRequest.setLastName("Smith" + getRandomIntNumber());
        addPersonRequest.setGender(new Util.RandomEnum<>(Gender.class).random());

        //todo
        LogRequest logRequest1 = new LogRequest();
        logRequest1.setTimestamp(LocalDateTime.now());
        logRequest1.setLogData(personDTO.toString());
        logService.addLog(logRequest1);

        PersonResponse addPersonResponse = addPersonService.addPerson(addPersonRequest);
        personDTO.setPersonalId(addPersonResponse.getPersonalId());
        personDTO.setDateOfBirth(addPersonResponse.getDateOfBirth());
        personDTO.setFirstName(addPersonResponse.getFirstName());
        personDTO.setLastName(addPersonResponse.getLastName());
        personDTO.setGender(addPersonResponse.getGender());

        return ResponseEntity.ok(personDTO);
    }

    @GetMapping(value = "/getAllPersons")
    public ResponseEntity<List<GetPersonDTO>> getAllPersonsByPage(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        Page<Person> personsPage = getPersonService.getAllPersons(pageNo, pageSize);

        var personsList = Collections.unmodifiableList(personsPage.get().collect(Collectors.toList()));

        //todo
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<GetPersonDTO>>() {
        }.getType();
        List<GetPersonDTO> personDTOS = modelMapper.map(personsList, listType);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalPages", String.valueOf(personsPage.getTotalPages()));
        responseHeaders.set("pageNumber", String.valueOf(personsPage.getNumber()));
        responseHeaders.set("pageSize", String.valueOf(personsPage.getSize()));
        responseHeaders.set("totalNumberOfElements", String.valueOf(personsPage.getTotalElements()));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(personDTOS);
    }

    @PostMapping(value = "/findPersonsByParams")
    public ResponseEntity<List<GetPersonDTO>> findPersonsByParams(@RequestBody GetPersonDTO personDTO) {

        PersonRequest getPersonRequest = new PersonRequest();
        getPersonRequest.setPersonalId(personDTO.getPersonalId());
        getPersonRequest.setDateOfBirth(personDTO.getDateOfBirth());
        getPersonRequest.setFirstName(personDTO.getFirstName());
        getPersonRequest.setLastName(personDTO.getLastName());
        getPersonRequest.setGender(personDTO.getGender());

        //todo json convertObjectToJson
        LogRequest logRequest1 = new LogRequest();
        logRequest1.setTimestamp(LocalDateTime.now());
        logRequest1.setLogData(personDTO.toString());
        logService.addLog(logRequest1);

        List<Person> personsList = getPersonService.findPersonsByParams(getPersonRequest);

        //todo
        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<GetPersonDTO>>() {}.getType();
        List<GetPersonDTO> personDTOS = modelMapper.map(personsList, listType);

        return ResponseEntity.ok(personDTOS);
    }

}
