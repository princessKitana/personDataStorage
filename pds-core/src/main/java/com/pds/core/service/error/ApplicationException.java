package com.pds.core.service.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException {

    private List<ApplicationError> errors;

}
