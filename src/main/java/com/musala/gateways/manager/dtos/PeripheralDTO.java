package com.musala.gateways.manager.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PeripheralDTO {
    private int uid;
    @ApiModelProperty( notes = "vendor name ",value = "CISCO",dataType = "java.lang.String")
    private String vendor;
    @ApiModelProperty( notes = "created date",value = "2021-07-27T12:34:56.401+00:00",dataType = "java.util.Date")
    private Date createdDate;
    @ApiModelProperty( notes = "status of Peripheral ",value = "true",dataType = "java.lang.Boolean")
    private Boolean status;

}
