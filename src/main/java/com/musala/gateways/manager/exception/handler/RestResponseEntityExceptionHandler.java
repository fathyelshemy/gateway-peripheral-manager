package com.musala.gateways.manager.exception.handler;

import com.musala.gateways.manager.dtos.ErrorResponseDTO;
import com.musala.gateways.manager.exception.ExceedPeripheralException;
import com.musala.gateways.manager.exception.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
        return new ResponseEntity<>(new ErrorResponseDTO(resourceNotFoundException.getMessage(), resourceNotFoundException.getCode()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceedPeripheralException.class)
    public ResponseEntity<ErrorResponseDTO> exceedPeripheralExceptionHandler(ExceedPeripheralException exceedPeripheralException){
        return new ResponseEntity<>(new ErrorResponseDTO(exceedPeripheralException.getMessage(), exceedPeripheralException.getCode()), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> duplicatedSerialNumberHandler(ConstraintViolationException constraintViolationException) {

        return new ResponseEntity<>(new ErrorResponseDTO("serial number is already exists",HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> invalidIPV4AttributeHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

        return new ResponseEntity<>(new ErrorResponseDTO("invalid ipv4 pattern",HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }
}
