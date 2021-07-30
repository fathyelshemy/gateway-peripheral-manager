package com.musala.gateways.manager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResourceNotFoundException extends RuntimeException {
    private final int code;
    private final String message;

}
