package utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import io.restassured.response.Response;

@Component
public class ApiUtils
{
    public String getBodyValue(Response response, String key)
    {
        return response.then().extract().path(key);
    }

    public ArrayList<HashMap<Object, String>> getListByKey(Response response, String key){
        return (ArrayList) response.jsonPath().getList(key);
    }

}
