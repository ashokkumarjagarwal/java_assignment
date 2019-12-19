package com.test.exception;

import com.test.responseDTO.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(basePackages = "com.test.controller")
@RestController
public class ExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ValidationException.class})
    public ErrorResponseDTO handleGenericException(ValidationException e) {
        ErrorResponseDTO err = new ErrorResponseDTO();
        err.setCode(e.getCode());
        err.setMessage(e.getMessage());
        return err;
    }

}
