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
public class GetPersonDTO extends PersonDTO {

    @ApiModelProperty(notes = "firstName", example = "John")
    private String firstName;

    @ApiModelProperty(notes = "lastName", example = "Smith")
    private String lastName;

    @ApiModelProperty(notes = "gender", example = "MALE")
    private Gender gender;

}
