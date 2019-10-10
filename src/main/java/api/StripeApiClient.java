package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.restassured.http.Header;
import io.restassured.http.Headers;

@Component
public class StripeApiClient extends ApiClient
{
    @Value("${bearer.token}")
    private String bearerToken;

    public static final String STRIPE_DOMAIN = "https://api.stripe.com/v1/";

    public StripeApiClient()
    {
        super(STRIPE_DOMAIN);
    }

    @Override
    public Headers getHeaders()
    {
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("Authorization", "Bearer " + bearerToken));
        headerList.add(new Header("Content-Type", "application/x-www-form-urlencoded"));
        return new Headers(headerList);
    }
}
