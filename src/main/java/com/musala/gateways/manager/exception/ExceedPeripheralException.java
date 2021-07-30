package com.musala.gateways.manager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExceedPeripheralException extends RuntimeException {
    private final int code;
    private final String message;
}
