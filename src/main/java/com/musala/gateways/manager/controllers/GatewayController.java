package com.musala.gateways.manager.controllers;

import com.musala.gateways.manager.dtos.GatewayDTO;
import com.musala.gateways.manager.services.GatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/")
@Api()
public class GatewayController {

    private GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }
    @ApiOperation(value = "add new gateway ",response = GatewayDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "gateway added successfully")
    })
    @PostMapping(value ="/gateway")
    public ResponseEntity<GatewayDTO> saveGateway(@Valid @RequestBody GatewayDTO gatewayDTO){

        return new ResponseEntity<>(gatewayService.saveGateway(gatewayDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "retrieve all gateways",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "gateway not found")
    })
    @GetMapping(value = "/gateway")
    public ResponseEntity<List<GatewayDTO>> retrieveAllGateways(){

        return new ResponseEntity<>(gatewayService.retrieveGateways(), HttpStatus.FOUND);
    }
    @ApiOperation(value = "retrieve gateway",response = GatewayDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Successfully gateway object"),
            @ApiResponse(code = 404, message = "gateway not found")
    })
    @GetMapping(value = "/gateway/{serialNumber}")
    public ResponseEntity<GatewayDTO> retrieveGateway(@PathVariable("serialNumber") String serialNumber) {
        return new ResponseEntity<>(gatewayService.retrieveBySerialNumber(serialNumber),HttpStatus.FOUND);
    }
}
