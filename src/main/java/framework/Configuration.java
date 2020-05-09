
package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public static String chromeDriverPath;
    public static String adminLoginUrl;
    public static String validEmail;
    public static String validPassword;
    
    public static void init() throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("resources/env.properties");
        prop.load(fis);
        
        chromeDriverPath = prop.getProperty("chromeDriverPath");
        adminLoginUrl = prop.getProperty("adminLoginUrl");
        validEmail = prop.getProperty("validEmail");
        validPassword = prop.getProperty("validPassword");
    }
   
}
