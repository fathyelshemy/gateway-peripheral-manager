package com.musala.gateways.manager.ut.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.musala.gateways.manager.controllers.GatewayController;
import com.musala.gateways.manager.dtos.GatewayDTO;
import com.musala.gateways.manager.services.GatewayService;
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

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GatewayController.class)
class GatewayControllerTest {

    @MockBean
    private GatewayService gatewayService;

    @Autowired private MockMvc mockMvc;

    GatewayDTO gatewayDTO;

    @BeforeEach
    void setUp() {
        gatewayDTO=new GatewayDTO();
        gatewayDTO.setId(1);
        gatewayDTO.setName("test name");
    }

    @Test
    void saveGateway() throws Exception {
        when(gatewayService.saveGateway(gatewayDTO)).thenReturn(gatewayDTO);
        String body= TestingUtil.ObjectMapperToString(gatewayDTO);
        mockMvc.perform(post("/gateway").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk());
    }

    @Test
    void retrieveAllGateways() throws Exception {
        when(gatewayService.retrieveGateways()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/gateway").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    void retrieveGateway() throws Exception {
        when(gatewayService.retrieveBySerialNumber(anyString())).thenReturn(new GatewayDTO());
        mockMvc.perform(get("/gateway/7569-9658-987-984").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());


    }
}