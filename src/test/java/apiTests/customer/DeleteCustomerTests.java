package apiTests.customer;



import api.model.Customer;
import api.steps.CustomersSteps;
import apiTests.AbstractApiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import utils.ApiUtils;

public class DeleteCustomerTests extends AbstractApiTest
{
    private Customer customer1;

    @Autowired
    private CustomersSteps customersSteps;

    @Autowired
    private ApiUtils apiUtils;

    @Value("${api.customer1.email}")
    private String emailCustomer1;

    @Value("${api.customer1.name}")
    private String nameCustomer1;

    @Value("${api.customer1.email}")
    private String descriptionCustomer1;

    @BeforeEach
    public void setUp()
    {
        customer1 = new Customer(emailCustomer1,nameCustomer1,descriptionCustomer1);
    }

    @Test
    public void deleteCustomerPositiveTest()
    {
        String customerId = apiUtils.getBodyValue(customersSteps.addCustomer(customer1), "id");
        int actualStatusCode = customersSteps.deleteCustomer(customerId).getStatusCode();
        Assertions.assertEquals(200, actualStatusCode);
    }
}
