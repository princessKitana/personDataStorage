package com.pds.core.service.person;

import com.pds.core.service.error.ApplicationError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonResponse extends PersonRequest {

    private List<ApplicationError> applicationErrors;

}
