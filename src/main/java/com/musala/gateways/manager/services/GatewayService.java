package com.musala.gateways.manager.services;

import com.musala.gateways.manager.dtos.GatewayDTO;
import com.musala.gateways.manager.entities.Gateway;
import com.musala.gateways.manager.exception.ExceedPeripheralException;
import com.musala.gateways.manager.exception.ResourceNotFoundException;
import com.musala.gateways.manager.repositories.GatewayRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GatewayService {

    private GatewayRepo gatewayRepo;

    private ModelMapper modelMapper;

    public GatewayService(GatewayRepo gatewayRepo, ModelMapper modelMapper) {
        this.gatewayRepo = gatewayRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public GatewayDTO saveGateway(GatewayDTO gatewayDTO){
        if(gatewayDTO.getPeripherals().size()>10)
            throw new ExceedPeripheralException(429,"can't add more than 10 Peripherals to same gateway");
        Gateway gateway=modelMapper.map(gatewayDTO,Gateway.class);
        gatewayRepo.save(gateway);
        return gatewayDTO;
    }

    public List<GatewayDTO> retrieveGateways(){
        return StreamSupport
                .stream(gatewayRepo.findAll().spliterator(),false)
                .map(gateway -> modelMapper.map(gateway,GatewayDTO.class))
                .collect(Collectors.toList());
    }

    public GatewayDTO retrieveBySerialNumber(String serialNumber){
        Gateway gateway=gatewayRepo.findGatewayBySerialNumber(serialNumber)
                .orElseThrow(()-> new ResourceNotFoundException(404,"serialNumber is not found"));
        return modelMapper.map(gateway,GatewayDTO.class);
    }
}
