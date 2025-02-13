package com.example.BitlyCloneApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExcetionHandler{

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundError(NoHandlerFoundException ex, Model model){
        model.addAttribute("resourceexception",ex.getMessage());
        return "exception";
    }

    @ExceptionHandler(InternalError.class)
    public String internalServerErrorPage(Exception ex,Model model){
        model.addAttribute("internalexception",ex.getMessage());
        return "exception";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementException(NoSuchElementException ex,Model model){
        model.addAttribute("nosuchelementexception",ex.getMessage());
        return "exception";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String badCredentials(BadCredentialsException ex,Model model){
        model.addAttribute("badcredentialsexception",ex.getMessage());
        return "exception";
     }


}




//400,401,402,403,404,405 bad credentioanal,resource not found , page not found
//500,501... internal server error
//200
//300 found res