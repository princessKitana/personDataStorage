package com.pds.core.api.common.v1;

import com.pds.core.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@NoArgsConstructor
@Getter
@Setter
public class AddPersonDTO extends PersonDTO {

    @ApiModelProperty(notes = "personalId", required = true, example = "210990-66399")
    private String personalId;

    @ApiModelProperty(notes = "dateOfBirth", required = true, example = "2000-09-08")
    private String dateOfBirth;

    @ApiModelProperty(notes = "firstName", readOnly = true)
    private String firstName;

    @ApiModelProperty(notes = "lastName", readOnly = true)
    private String lastName;

    @ApiModelProperty(notes = "gender", readOnly = true)
    private Gender gender;

}
