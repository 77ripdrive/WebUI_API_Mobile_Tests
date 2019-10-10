package apiTests.customer;



import api.model.Customer;
import api.steps.CustomersSteps;
import apiTests.AbstractApiTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import utils.ApiUtils;

public class CheckCustomerInfoTests extends AbstractApiTest
{
    private Customer customer1;
    private Response postedCustomerResponse;
    private String postedCustomerId;

    @Autowired
    private CustomersSteps customersSteps;

    @Autowired
    private ApiUtils apiUtils;

    @Value("${api.customer1.email}")
    private String emailCustomer1;

    @Value("${api.customer1.name}")
    private String nameCustomer1;

    @Value("${api.customer1.description}")
    private String descriptionCustomer1;

    @BeforeEach
    public void setUp()
    {
        customer1 = new Customer(emailCustomer1,nameCustomer1,descriptionCustomer1);
        postedCustomerResponse = customersSteps.addCustomer(customer1);
        postedCustomerId = apiUtils.getBodyValue(postedCustomerResponse, "id");
    }

    @AfterEach
    public void tearDown()
    {
        customersSteps.deleteCustomer(postedCustomerId);
    }

    @Test
    public void checkPostedCustomerName()
    {
        String actualCustomerName = apiUtils.getBodyValue(postedCustomerResponse, "name");
        Assertions.assertEquals(customer1.getName(), actualCustomerName);
    }

    @Test
    public void checkPostedCustomerDescription()
    {
        String actualCustomerName = apiUtils.getBodyValue(postedCustomerResponse, "description");
        Assertions.assertEquals(customer1.getDescription(), actualCustomerName);
    }

    @Test
    public void checkPostedCustomerEmail()
    {
        String actualCustomerName = apiUtils.getBodyValue(postedCustomerResponse, "email");
        Assertions.assertEquals(customer1.getEmail(), actualCustomerName);
    }
}
