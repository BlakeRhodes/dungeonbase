package com.vizientinc.dungeonbase.handlers.advisors;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomExceptionAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}