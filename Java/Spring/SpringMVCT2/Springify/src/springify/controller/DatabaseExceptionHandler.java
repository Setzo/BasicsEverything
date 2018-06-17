package springify.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler( DataAccessException.class )
    public String handleException(DataAccessException e) {

        return "error";
    }
}
