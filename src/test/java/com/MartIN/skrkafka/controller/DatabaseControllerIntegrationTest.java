package com.MartIN.skrkafka.controller;

import com.MartIN.skrkafka.entity.*;
import com.MartIN.skrkafka.repository.*;
import com.MartIN.skrkafka.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class
DatabaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DatabaseRepo databaseRepo;
    @MockBean
    private DatabaseServiceImpl databaseService;
    @Autowired
    private DatabaseServiceImpl service;
    @Test
    void shouldGet_saveDataFromWikiSucker() throws Exception {
        List<WikimediaData> wikiList = new ArrayList<>(
                Arrays.asList(
                        new WikimediaData(1l,123l,"AAA")
                )
        );
        List<Object> objectList = new ArrayList<Object>(wikiList);

        System.out.println("GGGGGGGGGGGGGGGGGG"+ objectList);
        when(databaseService.transformsArrayToList()).thenReturn(objectList);
        mockMvc.perform(get("/api/v1/database/data"))
//                .andDo(print())
                .andExpect(status().isOk());
        verify(databaseService,times(1)).saveData(objectList);

    }



}
