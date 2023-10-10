import org.json.JSONException;
import org.json.JSONObject;

public class JSONComparator {

    public static boolean compareJSON(String expected, String actual) {
        try {
            JSONObject expectedJSON = new JSONObject(expected);
            JSONObject actualJSON = new JSONObject(actual);
            return compareJSONObjects(expectedJSON, actualJSON);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean compareJSONObjects(JSONObject expected, JSONObject actual) {
        for (String key : expected.keySet()) {
            if (key.equals("ignore")) {
                continue;
            }

            if (!actual.has(key)) {
                System.out.println("Key '" + key + "' is missing in the actual JSON.");
                return false;
            }

            Object expectedValue = expected.get(key);
            Object actualValue = actual.get(key);

            if (expectedValue instanceof JSONObject && actualValue instanceof JSONObject) {
                if (!compareJSONObjects((JSONObject) expectedValue, (JSONObject) actualValue)) {
                    return false;
                }
            } else if (!expectedValue.equals(actualValue)) {
                System.out.println("Value mismatch for key '" + key + "': Expected - " + expectedValue + ", Actual - " + actualValue);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String expectedJSON = "{\"name\": \"John\", \"age\": 30, \"ignore\": \"extra field\"}";
        String actualJSON = "{\"name\": \"John\", \"age\": 31}";

        boolean result = compareJSON(expectedJSON, actualJSON);
        System.out.println("JSONs are equal: " + result);
    }
}
