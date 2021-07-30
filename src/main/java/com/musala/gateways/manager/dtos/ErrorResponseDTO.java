package com.musala.gateways.manager.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    @ApiModelProperty(notes = " message exception")
    private String message;
    @ApiModelProperty(notes = "Exception error code")
    private int code;
}
