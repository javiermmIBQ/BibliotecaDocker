package utils;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
	
	private final Properties properties;
	
    public ApplicationProperties(String file) throws RuntimeException {
    	properties = new Properties();
        try {
        	properties.load(getClass().getClassLoader().getResourceAsStream(file));
        } catch (IOException e) {
        	throw new RuntimeException("ERROR: failed to load properties file.",e);
        }
    }

    public String readProperty(String keyName) {
    	return properties.getProperty(keyName, "There is no key in the properties file");
    }


}
