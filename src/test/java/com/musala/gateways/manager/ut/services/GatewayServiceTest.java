package com.musala.gateways.manager.ut.services;

import com.musala.gateways.manager.dtos.GatewayDTO;
import com.musala.gateways.manager.dtos.PeripheralDTO;
import com.musala.gateways.manager.entities.Gateway;
import com.musala.gateways.manager.exception.ExceedPeripheralException;
import com.musala.gateways.manager.exception.ResourceNotFoundException;
import com.musala.gateways.manager.repositories.GatewayRepo;
import com.musala.gateways.manager.services.GatewayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GatewayServiceTest {

    @Mock
    private GatewayRepo gatewayRepo;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private GatewayService gatewayService;

    private GatewayDTO gatewayDTO;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        gatewayDTO= new GatewayDTO();
        gatewayDTO.setName("City star");
        gatewayDTO.setIpv4("127.0.0.1");
        gatewayDTO.setSerialNumber("9865-4544-3269-2588");
        gatewayDTO.setPeripherals(new ArrayList<PeripheralDTO>());
    }

    @Test
    void saveGateway_Successfully() {
//        when(gatewayDTO.getPeripherals().size()).thenReturn(1);
        Gateway gateway= new Gateway(1,"9865-4544-3269-2588","City star","127.0.0.1", Collections.EMPTY_LIST);
        when(modelMapper.map(gatewayDTO, Gateway.class)).thenReturn(gateway);
        when(gatewayRepo.save(gateway)).thenReturn(gateway);
        gatewayService.saveGateway(gatewayDTO);
        assertEquals(0,gateway.getPeripherals().size());
        assertEquals("City star",gateway.getName());
        assertEquals(1,gateway.getId());
        assertEquals("127.0.0.1",gateway.getIpv4());
        assertEquals("9865-4544-3269-2588",gateway.getSerialNumber());
    }
    @Test
    void saveGateway_whenThrowExceedLimitException() {
        List<PeripheralDTO> peripheralDTOList=new ArrayList<>();
        IntStream.range(0,11).forEach(i-> peripheralDTOList.add(new PeripheralDTO()) );
        gatewayDTO.setPeripherals(peripheralDTOList);
        assertThrows(ExceedPeripheralException.class,()-> gatewayService.saveGateway(gatewayDTO));
    }

    @Test
    void retrieveBySerialNumber_successfully() {
        Gateway gateway= new Gateway();
        gateway.setSerialNumber("9865-4544-3269-2588");
        gateway.setName("City star");
        gateway.setIpv4("127.0.0.1");
        when(gatewayRepo.findGatewayBySerialNumber("9865-4544-3269-2588"))
                .thenReturn(Optional.of(gateway));
        when(modelMapper.map(gateway,GatewayDTO.class)).thenReturn(gatewayDTO);
        GatewayDTO gatewayDTO=gatewayService.retrieveBySerialNumber("9865-4544-3269-2588");
        assertEquals("9865-4544-3269-2588",gateway.getSerialNumber());
        assertEquals("City star",gatewayDTO.getName());
        assertEquals("127.0.0.1",gatewayDTO.getIpv4());
    }

    @Test
    void retrieveBySerialNumber_gatewayNotFound() {
        when(gatewayRepo.findGatewayBySerialNumber(anyString()))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()-> gatewayService.retrieveBySerialNumber(anyString()));
    }
}