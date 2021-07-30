package com.musala.gateways.manager.ut.services;

import com.musala.gateways.manager.dtos.GatewayDTO;
import com.musala.gateways.manager.dtos.PeripheralDTO;
import com.musala.gateways.manager.entities.Peripheral;
import com.musala.gateways.manager.exception.ExceedPeripheralException;
import com.musala.gateways.manager.exception.ResourceNotFoundException;
import com.musala.gateways.manager.repositories.PeripheralRepo;
import com.musala.gateways.manager.services.GatewayService;
import com.musala.gateways.manager.services.PeripheralService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(MockitoExtension.class)
class PeripheralServiceTest {

    @Mock
    private GatewayService gatewayService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PeripheralRepo peripheralRepo;

    @InjectMocks
    private PeripheralService peripheralService;

    private GatewayDTO gatewayDTO;

    @BeforeEach
    void setUp() {
        gatewayDTO = new GatewayDTO();
        gatewayDTO.setIpv4("127.0.0.1");
        gatewayDTO.setName("City Star");
        gatewayDTO.setId(1);
        gatewayDTO.setSerialNumber("9865-4544-3269-2588");

    }

    @Test
    void addPeripheral_successfully() {
        List<PeripheralDTO> peripheralDTOList = new ArrayList<>();
        IntStream.range(0, 8).forEach(i -> peripheralDTOList.add(new PeripheralDTO()));
        gatewayDTO.setPeripherals(peripheralDTOList);
        when(gatewayService.retrieveBySerialNumber("9865-4544-3269-2588"))
                .thenReturn(gatewayDTO);
        peripheralService.addPeripheral("9865-4544-3269-2588", new PeripheralDTO());

        verify(gatewayService).saveGateway(gatewayDTO);
    }

    @Test
    void addPeripheral_ExceedLimitException() {
        List<PeripheralDTO> peripheralDTOList = new ArrayList<>();
        IntStream.range(0, 11).forEach(i -> peripheralDTOList.add(new PeripheralDTO()));
        gatewayDTO.setPeripherals(peripheralDTOList);
        when(gatewayService.retrieveBySerialNumber("9865-4544-3269-2588"))
                .thenReturn(gatewayDTO);

        assertThrows(ExceedPeripheralException.class,
                () -> peripheralService.addPeripheral("9865-4544-3269-2588", new PeripheralDTO()));

    }

    @Test
    void deletePeripheral_successfully() {

        List<PeripheralDTO> peripheralDTOList = new ArrayList<>();
        PeripheralDTO peripheralDTO = new PeripheralDTO();
        peripheralDTO.setUid(1);
        peripheralDTOList.add(peripheralDTO);
        gatewayDTO.setPeripherals(peripheralDTOList);
        when(gatewayService.retrieveBySerialNumber(anyString()))
                .thenReturn(gatewayDTO);

        Peripheral peripheral = new Peripheral(1, "", new Date(), true);
        when(peripheralRepo.findById(anyInt())).thenReturn(Optional.of(peripheral));

        when(modelMapper.map(peripheral, PeripheralDTO.class)).thenReturn(peripheralDTO);
        peripheralService.deletePeripheral(anyString(), 1);
        verify(gatewayService).saveGateway(gatewayDTO);
    }

    @Test
    void deletePeripheral_resourceNotFoundException() {
        when(gatewayService.retrieveBySerialNumber(anyString()))
                .thenReturn(gatewayDTO);
        when(peripheralRepo.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                ()-> peripheralService.deletePeripheral(anyString(),1));

    }
}