package com.pds.core.api.common.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {

    @ApiModelProperty(notes = "personalId", required = true, example = "210990-66399")
    private String personalId;

    @ApiModelProperty(notes = "dateOfBirth", required = true, example = "2000-09-08")
    private String dateOfBirth;

}
