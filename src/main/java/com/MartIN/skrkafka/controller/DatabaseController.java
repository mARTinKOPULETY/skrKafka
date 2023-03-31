package com.MartIN.skrkafka.controller;


import com.MartIN.skrkafka.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/database")
public class DatabaseController {




    @Autowired
    private DatabaseServiceImpl databaseService;

    public DatabaseController(DatabaseServiceImpl databaseService) {
        this.databaseService = databaseService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/data")
    public List<Object> saveDataFromWikiSucker(){
        //convert json format to List<Object>
        List<Object> listOfWikimediaData =databaseService.transformsArrayToList();
        databaseService.saveData(listOfWikimediaData);
        return listOfWikimediaData;
    }
}
