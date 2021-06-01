package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/24 - 05 - 24 - 11:12
 * @Description: util
 */
public class PropertiesUtil {
    private Properties properties;
    public PropertiesUtil(String path) {
        properties=new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getProperties(String key){
        return properties.getProperty(key);
    }
}
