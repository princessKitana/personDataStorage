package com.pds.core.service.person;


import com.pds.core.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonRequest {

    private String personalId;

    private String dateOfBirth;

    private String firstName;

    private String lastName;

    private Gender gender;

}
