package com.pds.core.service.user;


import com.pds.core.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AddUserRequest {

    private String personalId;

    private LocalDate dateOfBirth;

    private String firstName;

    private String lastName;

    private String gender;

}
