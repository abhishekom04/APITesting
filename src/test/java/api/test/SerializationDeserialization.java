package api.test;

import api.payload.POJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SerializationDeserialization {

    //Different methods of creating body of api request

    public void jsonObjectBodyMethod(){

        JSONObject bodyData = new JSONObject();
        bodyData.put("id",1);
        bodyData.put("name","test");
        bodyData.put("address",true);

        String[] multipleData ={"a","b","c"};
        bodyData.put("data",multipleData);
    }

    public void hashMapBodyMethod(){

        HashMap<Object,Object> bodyData = new LinkedHashMap();
        bodyData.put("id",1);
        bodyData.put("name","test");
        bodyData.put("address",true);

        String[] multipleData ={"a","b","c"};
        bodyData.put("data",multipleData);
    }

    public void pojoBodyMethod(){

        POJO pojoData = new POJO();
        pojoData.setId(1);
        pojoData.setUsername("test");
        pojoData.setEmail("test@xyz.com");

    }

    //Serialization pojo---->JSON
    @Test
    public void serialization() throws JsonProcessingException {

        POJO pojoData = new POJO();
        pojoData.setId(1);
        pojoData.setUsername("test");
        pojoData.setEmail("test@xyz.com");

        ObjectMapper mapper = new ObjectMapper();

        String jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoData);
        System.out.println(jsonData);


    }

    //Deserialization JSON---->pojo
    @Test
    public void deserialization() throws JsonProcessingException {

        String jsonData = "{\r\n"+
                "\"id\": 1,\r\n"+
                "\"username\":\"test\",\r\n"+
                "\"email\":\"test@xyz.com\",\r\n}";

        ObjectMapper mapper = new ObjectMapper();

        POJO pojoData = mapper.readValue(jsonData,POJO.class);
        System.out.println("id: "+pojoData.getId());
        System.out.println("username: "+pojoData.getUsername());
        System.out.println("email: "+pojoData.getEmail());

    }
}
