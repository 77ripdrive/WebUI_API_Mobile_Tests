package api.model;

public class Customer
{
    private String email;
    private String name;
    private String description;

    public Customer(String email, String name, String description) {
        this.email = email;
        this.name = name;
        this.description = description;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}
