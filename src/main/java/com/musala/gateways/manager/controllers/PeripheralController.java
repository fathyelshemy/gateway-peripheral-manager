package com.musala.gateways.manager.controllers;

import com.musala.gateways.manager.dtos.GatewayDTO;
import com.musala.gateways.manager.dtos.PeripheralDTO;
import com.musala.gateways.manager.services.PeripheralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "peripheral operations")
public class PeripheralController {

    private PeripheralService peripheralService;

    public PeripheralController(PeripheralService peripheralService) {
        this.peripheralService = peripheralService;
    }

    @ApiOperation(value = "add new peripheral",response = GatewayDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 429, message = "can't add more than 10 peripheral for single gateway")
    })
    @PostMapping("/gateway/{serialNumber}")
    public ResponseEntity<GatewayDTO> addPeripheral(@PathVariable("serialNumber")String serialNumber, @RequestBody PeripheralDTO peripheralDTO) {

        return new ResponseEntity<>(peripheralService.addPeripheral(serialNumber,peripheralDTO), HttpStatus.OK);
    }
    @ApiOperation(value = "delete exist peripheral")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully delete peripheral"),
            @ApiResponse(code = 404, message = "peripheral not found")
    })
    @DeleteMapping("/gateway/{serialNumber}/{uid}")
    public ResponseEntity<HttpStatus> delPeripheral(@PathVariable("serialNumber")String serialNumber,@PathVariable("uid") int uid){
        peripheralService.deletePeripheral(serialNumber,uid);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
