package com.pds.core.api.error.v1;

import com.pds.core.service.error.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationExceptionDTO {

    private List<ApplicationError> errors;

}
