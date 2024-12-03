import jdk.jfr.Description;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
*  Class lay tham so tu file config
*/
public class ConfigUtil {
    private static final Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(ConfigUtil.class);

    static {
        try (FileInputStream input = new FileInputStream("./src/config.properties")) {
            logger.info("Bat dau tai file config....");
            properties.load(input);
            logger.info("Tai file config thanh cong.");
        } catch (IOException e) {
            logger.fatal("Khong the tai file ./src/config.properties", e);
        }
    }

    @Description("Lay tham so they key")
    public static String get (String key){
        String result = properties.getProperty(key);
        if (result == null)
            logger.warn("Khong tim thay " + key);
        return result;
    }

    @Description("Lay tham so, kem gia tri mac dinh")
    public  static String get (String key, String defaultValue){
        String result = properties.getProperty(key, defaultValue);
        if (result.equals(defaultValue))
            logger.warn("Khong tim thay " + key + ", dung gia tri mac dinh.");
        return result;
    }
}
