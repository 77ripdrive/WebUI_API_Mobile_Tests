package api.steps;



import api.StripeApiClient;
import api.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.restassured.response.Response;

import static utils.StatusCodeUtil.verifyStatusCodeOK;

@Component
public class CustomersSteps
{
    private static final String CUSTOMERS_ENDPOINT = "customers";
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomersSteps.class);

    @Autowired
    private StripeApiClient stripeClient;

    public String getCustomerState(Customer customer)
    {
        return String.format("name=%s&email=%s&description=%s", customer.getName(), customer.getEmail(),
                customer.getDescription());
    }

    public Response addCustomer(Customer customer)
    {
        String body = getCustomerState(customer);
        Response response = stripeClient.post(CUSTOMERS_ENDPOINT, body);
        verifyStatusCodeOK(response);
        LOGGER.info("The customer was added");
        return response;
    }

    public Response deleteCustomer(String customerId)
    {
        Response response = stripeClient.delete(CUSTOMERS_ENDPOINT + "/" + customerId);
        verifyStatusCodeOK(response);
        LOGGER.info("The customer was deleted");
        return response;
    }
}
