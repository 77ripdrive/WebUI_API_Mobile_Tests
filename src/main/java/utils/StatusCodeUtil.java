package utils;

import static io.restassured.internal.http.Status.SUCCESS;


import api.exceptions.FailureStatusException;
import io.restassured.response.Response;

public class StatusCodeUtil
{
    public static void verifyStatusCodeOK(Response response)
    {
        int statusCode = response.getStatusCode();
        if (!SUCCESS.matches(statusCode))
        {
            throw new FailureStatusException(statusCode,
                    "Request failed - status code is not OK :" + response.getBody());
        }
    }
}
