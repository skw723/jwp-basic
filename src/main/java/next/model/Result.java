package next.model;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public static Map<String, Object> ok() {
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        return map;
    }

    public static Map<String, Object> fail(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);
        map.put("message", message);
        return map;
    }
}
