package com.example.tdd.Vehicles.Exception;

import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.tdd.Vehicles.Model.Error;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

        @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class})
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<Object> handleConstraintViolation(
                ConstraintViolationException constraintViolationException, WebRequest request) {

            List<String> errors = new ArrayList<>();

            for (ConstraintViolation<?> violation :
                    constraintViolationException.getConstraintViolations()) {

                errors.add(violation.getRootBeanClass().getName() + " " +
                        violation.getPropertyPath() + ": " + violation.getMessage());
            }

            Error err = new Error(HttpStatus.BAD_REQUEST,errors,new Date());

            return new ResponseEntity<Object>(
                    err,err.getStatus());

        }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        Error apiError =
                new Error(HttpStatus.BAD_REQUEST,errors,new Date() );

        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);


    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {VehicleNotFoundException.class,
            VehiclePriceNotFoundException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add("This APi doesn't exist or is unreachable");

        Error error= new Error(HttpStatus.NOT_FOUND,errors,new Date());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}

