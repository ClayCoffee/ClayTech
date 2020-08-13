package cn.claycoffee.ClayTech.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class StrUtils {
    public static String getLeftStr(String bep, String leftstr) {
        if (bep.contains(leftstr)) {
            int index = bep.indexOf(leftstr);
            index -= 1;
            String str = "";
            char[] ca = bep.toCharArray();
            for (int i = 0; i <= index; i++) {
                str += ca[i];
            }
            return str;
        } else {
            return null;
        }
    }

    public static String getRightStr(String bep, String rightstr) {
        if (bep.contains(rightstr)) {
            int index = bep.indexOf(rightstr);
            index += rightstr.length();
            index += 1;
            String str = "";
            char[] ca = bep.toCharArray();
            for (; index <= bep.length(); index++) {
                str += ca[index - 1];
            }
            return str;
        } else {
            return null;
        }
    }

    public static Map<String, String> parseJSON(String json) {
        Map<String, String> ret = new HashMap<>();

        if (json != null && json.length() > 2) {
            JsonParser p = new JsonParser();
            JsonObject obj = p.parse(json).getAsJsonObject();

            for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                ret.put(entry.getKey(), entry.getValue().getAsString());
            }
        }
        return ret;
    }
}
