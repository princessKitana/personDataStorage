package com.pds.core.controller;

import com.pds.core.api.error.v1.ApplicationExceptionDTO;
import com.pds.core.enums.BusinessRules;
import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.error.ApplicationException;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //TODO log errors

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<Object> handleApplicationException(ApplicationException ex, WebRequest request) {
        log.info(new GsonBuilder().setPrettyPrinting().create().toJson(ex));

        ApplicationExceptionDTO appExDTO = new ApplicationExceptionDTO(ex.getErrors());

        return handleExceptionInternal(ex, appExDTO,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error(String.valueOf(ex));
        List<ApplicationError> errors = new ArrayList<>();

        ApplicationError error = new ApplicationError();
        error.setStatusCode(BusinessRules.BR_9999.name());
        error.setDescription(BusinessRules.BR_9999.getMessage());
        error.setType("ERROR");
        error.setSeverity("FATAL");
        error.setDebugStacktrace(Arrays.toString(ex.getStackTrace()));

        errors.add(error);
        ApplicationExceptionDTO appExDTO = new ApplicationExceptionDTO(errors);
        log.error(new GsonBuilder().setPrettyPrinting().create().toJson(appExDTO));

        return handleExceptionInternal(ex, appExDTO,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
