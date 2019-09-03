package com.example.tdd.Vehicles.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.tdd.Vehicles.Model.Error;

@ControllerAdvice
public class ExceptionHandler {

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
}

