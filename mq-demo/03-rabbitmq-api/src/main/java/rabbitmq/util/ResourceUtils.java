package rabbitmq.util;

import java.util.ResourceBundle;

public class ResourceUtils extends org.springframework.util.ResourceUtils {
    private static final ResourceBundle resourceBundle;
    static {
        resourceBundle = ResourceBundle.getBundle("mq");
    }

    public static String getKey(String key) {
        return resourceBundle.getString(key);
    }
}
