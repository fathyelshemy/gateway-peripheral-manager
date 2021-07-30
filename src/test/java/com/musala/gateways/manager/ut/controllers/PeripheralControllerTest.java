package com.musala.gateways.manager.ut.controllers;

import com.musala.gateways.manager.controllers.GatewayController;
import com.musala.gateways.manager.controllers.PeripheralController;
import com.musala.gateways.manager.dtos.GatewayDTO;
import com.musala.gateways.manager.dtos.PeripheralDTO;
import com.musala.gateways.manager.services.GatewayService;
import com.musala.gateways.manager.services.PeripheralService;
import com.musala.gateways.manager.ut.utils.TestingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PeripheralController.class)
class PeripheralControllerTest {

    @MockBean
    private PeripheralService peripheralService;

    @Autowired
    private MockMvc mockMvc;

    GatewayDTO gatewayDTO;
    PeripheralDTO peripheralDTO;

    @BeforeEach
    void setUp(){
        gatewayDTO=new GatewayDTO();
        gatewayDTO.setId(1);
        gatewayDTO.setName("test name");
        peripheralDTO= new PeripheralDTO();
        peripheralDTO.setUid(1);
        peripheralDTO.setStatus(true);
        peripheralDTO.setCreatedDate(new Date());
        peripheralDTO.setVendor("musala");
        gatewayDTO.setPeripherals(Arrays.asList(peripheralDTO));

    }
    @Test
    void addPeripheral() throws Exception{
        when(peripheralService.addPeripheral("3699-872-6498-5155", peripheralDTO))
                .thenReturn(new GatewayDTO());
        String body= TestingUtil.ObjectMapperToString(peripheralDTO);
        mockMvc.perform(post("/gateway/3699-872-6498-5155").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk());
    }

    @Test
    void delPeripheral() throws Exception {
    doNothing().when(peripheralService).deletePeripheral(anyString(),anyInt());
    mockMvc.perform(delete("/gateway/3699-872-6498-5155/1").contentType(MediaType.APPLICATION_JSON)
            .param("serialNumber","3699-872-6498-5155")
            .param("uid","1")
    ).andExpect(status().isNoContent());
    }
}