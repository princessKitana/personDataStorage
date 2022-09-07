package com.pds.core.controller;


import com.pds.core.api.common.v1.UserDTO;
import com.pds.core.enums.Gender;
import com.pds.core.service.user.AddUserRequest;
import com.pds.core.service.user.AddUserResponse;
import com.pds.core.service.user.add.AddUserService;
import com.pds.core.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pds.core.util.Util.getRandomIntNumber;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    AddUserService userService;

    @PostMapping(value = "/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setPersonalId(userDTO.getPersonalId());
        addUserRequest.setDateOfBirth(userDTO.getDateOfBirth());
        addUserRequest.setFirstName("firstName"+ getRandomIntNumber());
        addUserRequest.setLastName("lastName"+ getRandomIntNumber());
        addUserRequest.setGender(String.valueOf(new Util.RandomEnum<>(Gender.class).random()));

        AddUserResponse addUserResponse = userService.register(addUserRequest);
        userDTO.setPersonalId(addUserResponse.getPersonalId());
        userDTO.setDateOfBirth(addUserResponse.getDateOfBirth());
        userDTO.setFirstName(addUserResponse.getFirstName());
        userDTO.setLastName(addUserResponse.getLastName());
        userDTO.setGender(addUserResponse.getGender());

        return ResponseEntity.ok(userDTO);
    }

}
