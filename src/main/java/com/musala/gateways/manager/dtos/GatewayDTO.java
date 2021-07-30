package com.musala.gateways.manager.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;
@Data
public class GatewayDTO {

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private long id;
    @ApiModelProperty(required = true , notes = "unique serial number for each device",value = "8569-23658-3658-dfds8",dataType = "java.lang.String")
    private String serialNumber;
    @ApiModelProperty( notes = "readable name for gateway",value = "CISCO",dataType = "java.lang.String")
    private String name;
    @ApiModelProperty( notes = "ipv4 of gateway",value = "127.0.0.1",dataType = "java.lang.String")
    @Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}$",message = "invalid ipv4 pattern")
    private String ipv4;
    @ApiModelProperty( notes = "list of peripherals ",dataType = "java.util.List")
    private List<PeripheralDTO> peripherals;

}
