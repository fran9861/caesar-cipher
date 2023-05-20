package com.caesar.cipher.caesarcipher.consumer;

import com.caesar.cipher.caesarcipher.DummyMock;
import com.caesar.cipher.caesarcipher.crosscutting.ConstantPath;
import com.caesar.cipher.caesarcipher.openapi.model.SendResponseCaesarCipher;
import com.caesar.cipher.caesarcipher.service.CaesarCipherImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebMvcTest
@ContextConfiguration(classes = CaesarCipherController.class)
@ActiveProfiles("test")
class CaesarCipherControllerTest {

    private MockMvc mockMvc;


    @MockBean
    private CaesarCipherImpl iCaesarCipher;

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void createCaesarCipher() throws Exception {

        Mockito.lenient().when(iCaesarCipher.caesarCipherText(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(ResponseEntity.ok(SendResponseCaesarCipher
                                .builder()
                                .text("prueba")
                                .build()));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(
                HttpMethod.POST, ConstantPath.BASE_PATH_V1+ConstantPath.CAESAR_CIPHER_PATH)
                .content(objectMapper.writeValueAsString(DummyMock.sendRequestCaesarCipher()))
                .contentType("application/json");

        mockMvc.perform(requestBuilder
                .header("x-b3-traceid","421414454a35e11q")
                )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void getCaesarCipherList() throws Exception {

        Mockito.lenient().when(iCaesarCipher.caesarCipherList(Mockito.anyInt(), Mockito.anyInt())).thenReturn(DummyMock.sendResponseCaesarCipherList());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(
                        HttpMethod.GET, ConstantPath.BASE_PATH_V1+ConstantPath.CAESAR_CIPHER_LIST_PATH)
                .content(objectMapper.writeValueAsString(DummyMock.sendRequestCaesarCipher()));

        mockMvc.perform(requestBuilder
                        .header("x-b3-traceid","421414454a35e11q")
                )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
