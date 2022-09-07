package com.pds.core.service.user;


import com.pds.core.enums.Gender;
import com.pds.core.service.error.ApplicationError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddUserResponse {

    private String personalId;

    private LocalDate dateOfBirth;

    private String firstName;

    private String lastName;

    private Gender gender;

    private List<ApplicationError> applicationErrors;

}
