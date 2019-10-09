package webDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import utils.ResourceUtils;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Docker {

    @Value("${docker.compose}")
    private String dockerCompose;

    private static final Logger LOGGER = LoggerFactory.getLogger(Docker.class);

    public void startSeleniumGrid()
    {
        LOGGER.info("Starting test container");
        runCommand("docker-compose -f " + getComposeFilePath() + " up -d");
    }

    public void stopSeleniumGrid()
    {
        LOGGER.info("Stop container");
        runCommand("docker-compose stop " + getComposeFilePath());
    }

    private String getComposeFilePath()
    {
        URI uri;
        try
        {
            uri = ResourceUtils.findResource(Docker.class, dockerCompose);
            if (uri == null)
            {
                throw new IllegalStateException("Couldn't get resource file for '" + dockerCompose);
            }
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException("Couldn't get URI of " + dockerCompose + "." + e.getMessage());
        }
        return new File(uri).getAbsolutePath();
    }

    private static void runCommand(String command)
    {
        try
        {
            Runtime.getRuntime().exec(command);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error occurred during executing command '" + command + "'. " + e.getMessage());
        }
    }
}
