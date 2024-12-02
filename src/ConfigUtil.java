import jdk.jfr.Description;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
*  Class lay tham so tu file config
*/
public class ConfigUtil {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("./src/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Description("Lay tham so they key")
    public static String get (String key){
        return properties.getProperty(key);
    }

    @Description("Lay tham so, kem gia tri mac dinh")
    public  static String get (String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }
}
