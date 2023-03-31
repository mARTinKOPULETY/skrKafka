
package com.MartIN.skrkafka.controller;

import com.MartIN.skrkafka.entity.*;
import com.MartIN.skrkafka.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class DatabaseControllerUnitTest {



    @Mock
    DatabaseServiceImpl databaseService =new DatabaseServiceImpl();

   @InjectMocks
    DatabaseController databaseController= new DatabaseController(databaseService);




    @Test
    void testSaveDataFromWikiSucker_whenIsProvidedWikimediaList_returnWikimediaList() {
//        MockitoAnnotations.openMocks(this);
        List<WikimediaData> wikiList = new ArrayList<>(
                Arrays.asList(
                        new WikimediaData(1l,123l,"AAA")
                )
        );
        List<Object> objectListExpected = new ArrayList<Object>(wikiList);


        when(databaseService.transformsArrayToList()).thenReturn(objectListExpected);

        databaseController.saveDataFromWikiSucker();
//        verify(databaseService,times(1)).saveData(objectListExpected);
        assertEquals(objectListExpected , databaseController.saveDataFromWikiSucker());
        System.out.println(objectListExpected);
        System.out.println(databaseController.saveDataFromWikiSucker());
    }


}
