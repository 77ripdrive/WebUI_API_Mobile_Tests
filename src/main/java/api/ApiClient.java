package api;

import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.net.URL;


import api.exceptions.FailureStatusException;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import utils.StatusCodeUtil;

public abstract class ApiClient
{
    private Response response;
    private URL requestUrl;
    private String domain;

    protected ApiClient(String domain)
    {
        this.domain = domain;
    }

    public abstract Headers getHeaders();

    public Response get(String apiEndpoint)
    {
        return response = given().headers(getHeaders()).get(getRequestUrl(apiEndpoint));
    }

    public Response post(String apiEndpoint, String body)
    {
        return response = given().headers(getHeaders()).body(body).post(getRequestUrl(apiEndpoint));
    }

    public Response delete(String apiEndpoint)
    {
        return response = given().headers(getHeaders()).delete(getRequestUrl(apiEndpoint));
    }

    public int getStatusCode()
    {
        return response.getStatusCode();
    }

    public boolean isStatusCodeOk()
    {
        try
        {
            StatusCodeUtil.verifyStatusCodeOK(response);
        }
        catch (FailureStatusException e)
        {
            return false;
        }
        return true;
    }

    public Response getResponse()
    {
        return response;
    }

    public void setResponse(Response response)
    {
        this.response = response;
    }

    public URL getRequestUrl(String apiEndpoint)
    {
        try
        {
            requestUrl = new URL(domain + apiEndpoint);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
        return requestUrl;
    }

    public void setRequestUrl(URL requestUrl)
    {
        this.requestUrl = requestUrl;
    }

    public URL getRequestUrl()
    {
        return requestUrl;
    }
}
