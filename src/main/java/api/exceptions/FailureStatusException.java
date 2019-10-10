package api.exceptions;

public class FailureStatusException extends RuntimeException
{
    private int statusCode;
    private String message;

    public FailureStatusException(int statusCode, String message)
    {
        this.statusCode = statusCode;
        this.message = message;
    }
}
