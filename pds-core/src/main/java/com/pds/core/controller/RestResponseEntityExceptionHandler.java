package com.pds.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pds.core.api.common.v1.PersonDTO;
import com.pds.core.api.error.v1.ApplicationExceptionDTO;
import com.pds.core.enums.BusinessRules;
import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.error.ApplicationException;
import com.google.gson.GsonBuilder;

import com.pds.core.service.log.LogRequest;
import com.pds.core.service.log.add.AddLogService;
import com.pds.core.util.Util;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AddLogService logService;

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<Object> handleApplicationException(ApplicationException ex, WebRequest request) {
        var errorMessage = new GsonBuilder().setPrettyPrinting().create().toJson(ex);
        logger.info(errorMessage);
        logErrorMessage(errorMessage, request.getDescription(true));

        ApplicationExceptionDTO appExDTO = new ApplicationExceptionDTO(ex.getErrors());

        return handleExceptionInternal(ex, appExDTO,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        logger.error(String.valueOf(ex));
        List<ApplicationError> errors = new ArrayList<>();

        ApplicationError error = new ApplicationError();
        error.setStatusCode(BusinessRules.BR_9999.name());
        error.setDescription(BusinessRules.BR_9999.getMessage());
        error.setType("ERROR");
        error.setSeverity("FATAL");
        error.setDebugStacktrace(Arrays.toString(ex.getStackTrace()));

        errors.add(error);
        ApplicationExceptionDTO appExDTO = new ApplicationExceptionDTO(errors);

        var errorMessage = new GsonBuilder().setPrettyPrinting().create().toJson(appExDTO);
        logErrorMessage(errorMessage, request.getDescription(true));
        logger.error(errorMessage);

        return handleExceptionInternal(ex, appExDTO,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private void logErrorMessage(String error, String name) {
            var logRequest = new LogRequest();
            logRequest.setTimestamp(LocalDateTime.now());
            logRequest.setLogData(error);
            logRequest.setName(name);
            logService.addLog(logRequest);

    }

}
