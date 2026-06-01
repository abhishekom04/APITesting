package api.utilities;

import api.payload.POJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class demo {

    public static void main(String[] args) throws JsonProcessingException {

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
