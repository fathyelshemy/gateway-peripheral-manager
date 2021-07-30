package com.musala.gateways.manager.services;

import com.musala.gateways.manager.dtos.GatewayDTO;
import com.musala.gateways.manager.dtos.PeripheralDTO;
import com.musala.gateways.manager.entities.Peripheral;
import com.musala.gateways.manager.exception.ExceedPeripheralException;
import com.musala.gateways.manager.exception.ResourceNotFoundException;
import com.musala.gateways.manager.repositories.PeripheralRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeripheralService {

    private GatewayService gatewayService;

    private ModelMapper modelMapper;

    private PeripheralRepo peripheralRepo;

    public PeripheralService(GatewayService gatewayService, ModelMapper modelMapper, PeripheralRepo peripheralRepo) {
        this.gatewayService = gatewayService;
        this.modelMapper = modelMapper;
        this.peripheralRepo = peripheralRepo;
    }
    @Transactional
    public GatewayDTO addPeripheral(String serialNumber, PeripheralDTO peripheralDTO){
        GatewayDTO gatewayDTO=gatewayService.retrieveBySerialNumber(serialNumber);

        if(gatewayDTO.getPeripherals().size()<10) {
            gatewayDTO.getPeripherals().add(peripheralDTO);
            return gatewayService.saveGateway(gatewayDTO);
        }else {
            throw new ExceedPeripheralException(429,"can't add more than 10 Peripherals to same gateway");
        }
    }

    public void deletePeripheral(String serialNumber,int uid) {
        GatewayDTO gatewayDTO=gatewayService.retrieveBySerialNumber(serialNumber);
        Peripheral peripheral=peripheralRepo.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException(404,"uid is not found"));
        gatewayDTO.getPeripherals().remove(modelMapper.map(peripheral,PeripheralDTO.class));
        gatewayService.saveGateway(gatewayDTO);
    }

}
