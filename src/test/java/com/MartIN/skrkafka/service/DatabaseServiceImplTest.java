package com.MartIN.skrkafka.service;

import com.MartIN.skrkafka.entity.*;
import com.MartIN.skrkafka.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DatabaseServiceImplTest {
    @Mock
     RestTemplate restTemplate;

    @Mock
    DatabaseRepo databaseRepo;
    @InjectMocks
    DatabaseServiceImpl databaseService= new DatabaseServiceImpl() ;

    @Test
    @DisplayName("Can get array from another app by REST.")
    public void testGetArrayOfObjects_whenArrayIsProvided_returnArrayOfObjects() {
        Object[] objects = new Object[1];
        objects[0]= new WikimediaData(1l, "ABX");

        Mockito
                .when(restTemplate.getForEntity(
                        "http://localhost:8010/api/v1/consumer",  Object[].class))
          .thenReturn(new ResponseEntity<>(objects, HttpStatus.OK));

        Object[] obj= databaseService.getArrayOfObjects();

        Assertions.assertEquals(objects,obj);
    }



    @Test
    @DisplayName("Can get empty array from another app by REST.")
    public void testGetArrayOfObjects_whenArrayIsNOTProvided_returnEmptyArrayOfObjects() {
        Object[] objects = new Object[0];

        Mockito
                .when(restTemplate.getForEntity(
                        "http://localhost:8010/api/v1/consumer",  Object[].class))
                .thenReturn(new ResponseEntity<>(objects, HttpStatus.OK));

        Object[] obj= databaseService.getArrayOfObjects();

        Assertions.assertEquals(objects,obj);
    }

    @Test
    @DisplayName("Can transform Object[] to List<Object>")
    public void testTransformsArrayToList_whenArrayIsProvided_returnsListOfObjects(){
        Object[] objects = new Object[1];
        objects[0]= new WikimediaData(1l, "ABX");

        Mockito
                .when(restTemplate.getForEntity(
                        "http://localhost:8010/api/v1/consumer",  Object[].class))
                .thenReturn(new ResponseEntity<>(objects, HttpStatus.OK));

        List<Object> givenData =  Arrays.asList(objects);
        List<Object> expectedData= databaseService.transformsArrayToList();
       assertEquals(givenData,expectedData);

    }

    @Test
    @DisplayName("Can throw NullPointerException when data for transform Object[] to List<Object> is not provided ")
    public void testTransformsArrayToList_whenArrayIsNOTProvided_returnsNullPointerException(){
        Object[] objects = new Object[1];
        objects[0]= new WikimediaData(1l, "ABX");

        List<Object> givenData =  Arrays.asList(objects);


        assertThrows(NullPointerException.class,  ()->
                        databaseService.transformsArrayToList() ,
                "Should have thrown IndexOutOfBoundsException");
    }




    @Test
    @DisplayName("Can type cast ")
    void testSaveMethod_whenIsGivenObjectList_listIsTypeCastSuccesfully(){

        Object[] objects = new Object[1];
        objects[0]= new WikimediaData(1l, "ABX");
        List<Object> listek =  Arrays.asList(objects);


        List<WikimediaData> expectedData = new ArrayList<>();
        expectedData.add(new WikimediaData(1l, "ABX"));
        databaseService.saveData(listek);
        List<WikimediaData> wikimediaDataList = (List<WikimediaData>)(Object)listek;


        assertEquals(expectedData, wikimediaDataList);
    }

    @Test
    @DisplayName("Can type cast ")
    void XtestSaveMethod_whenIsGivenObjectList_listIsTypeCastSuccesfully(){
        databaseService.setLastId(1);
//        databaseService.setLastId(0);
        Object[] objects = new Object[1];
        objects[0]= new WikimediaData(1l, "ABX");
        List<Object> listek =  Arrays.asList(objects);


        List<WikimediaData> expectedData = new ArrayList<>();
        expectedData.add(new WikimediaData(1l, "ABX"));
        databaseService.saveData(listek);
        List<WikimediaData> wikimediaDataList = (List<WikimediaData>)(Object)listek;


        assertEquals(expectedData, wikimediaDataList);
    }









}
