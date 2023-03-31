package com.MartIN.skrkafka.service;


import com.MartIN.skrkafka.entity.*;
import com.MartIN.skrkafka.repository.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import java.util.*;
import java.util.stream.*;

@Service
public class DatabaseServiceImpl {






    private int lastId = 0;

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    @Autowired
    private DatabaseRepo databaseRepo;

    RestTemplate restTemplate = new RestTemplate();



    public void saveData(List<Object> listek ){
        System.out.println("LASTID:"+ lastId);
        List<WikimediaData> wikimediaDataList = (List<WikimediaData>)(Object)listek;
        //returns empty list, not out of IndexOutOfBoundsException, if wikimediaDataList is  empty
        if(wikimediaDataList.size()> 0){

        if(lastId!=0) {
            List<WikimediaData> notDuplicateId = wikimediaDataList.stream()
                    .filter(x -> x.getOriginalId() > lastId)
                    .collect(Collectors.toList());
            databaseRepo.saveAll(notDuplicateId);
            lastId = (wikimediaDataList.get(wikimediaDataList.size() -1 )
                    .getOriginalId()).intValue();
        } else {
            lastId = (wikimediaDataList.get(wikimediaDataList.size() -1  )
                    .getOriginalId()).intValue();
            databaseRepo.saveAll(wikimediaDataList);
        }
        }
    }

    //gets json  from url & make Object[]
    public Object[] getArrayOfObjects(){

        String url = "http://localhost:8010/api/v1/consumer";

        ResponseEntity<Object[]> responseEntity =
                restTemplate.getForEntity(url, Object[].class);
        Object[] objects = responseEntity.getBody();
        List<Object> dataFromEther= Arrays.asList(objects);
        return objects;
    }


    //transforms  Object[] objects to List<Object>. .map(object -> mapper.convertValue(object, WikimediaData.class)
    //it is mandatory, if not, throws  hashmap error
    public List<Object> transformsArrayToList(){
        Object[] objects= getArrayOfObjects();
        ObjectMapper mapper = new ObjectMapper();
        List<Object> dataFromEther= Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, WikimediaData.class))

                .collect(Collectors.toList());
        return dataFromEther;
    }



}
