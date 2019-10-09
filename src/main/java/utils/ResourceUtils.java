package utils;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Set;

public class ResourceUtils {
    public DesiredCapabilities setUpAppiumDriver(ConfigurableEnvironment env, String prefix) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Properties desiredCapabilitiesProperties = getProperties(env, prefix);
        Set <String> keys = desiredCapabilitiesProperties.stringPropertyNames();
        for (String key : keys) {
            String value = desiredCapabilitiesProperties.getProperty(key);
            desiredCapabilities.setCapability(key.replace(prefix, ""), value);
        }
        return desiredCapabilities;
    }

    private Properties getProperties(ConfigurableEnvironment env, String prefix) {
        Properties props = new Properties();
        for (PropertySource <?> source : env.getPropertySources()) {
            if (source instanceof EnumerablePropertySource) {
                EnumerablePropertySource <?> enumerable = (EnumerablePropertySource <?>) source;
                for (String name : enumerable.getPropertyNames()) {
                    if (name.startsWith(prefix)) {
                        props.putIfAbsent(name, enumerable.getProperty(name));
                    }
                }
            }
        }
        return props;
    }

    public static URI findResource(Class <?> clazz, String path) throws URISyntaxException {
        return clazz.getResource(path).toURI();
    }
}
