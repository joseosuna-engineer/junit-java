package com.prottonne.testing.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.prottonne.testing.dto.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GlobalExceptionHandler() {
        super();
    }

    @ExceptionHandler(value = SomeException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response someExceptionHandler(Exception ex) {

        SomeException someException = (SomeException) ex;

        logger.error(ex.getMessage(), someException);

        Response response = new Response();
        response.setMessage(ex.getMessage());

        return response;
    }

}
