package com.pds.core.api.common.v1;

import com.pds.core.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@ApiModel
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    @ApiModelProperty(notes = "personalId", required = true, example = "210990-66399")
    private String personalId;

    @ApiModelProperty(notes = "dateOfBirth", required = true)
    private LocalDate dateOfBirth;

    @ApiModelProperty(notes = "firstName", readOnly = true)
    private String firstName;

    @ApiModelProperty(notes = "lastName", readOnly = true)
    private String lastName;

    @ApiModelProperty(notes = "gender", readOnly = true)
    private Gender gender;

}
