import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

public class JSONComparatorTest {

    @Test
    public void testEqualJSONs() {
        String expectedJSON = "{\"name\": \"John\", \"age\": 30}";
        String actualJSON = "{\"name\": \"John\", \"age\": 30}";

        JsonPath expected = new JsonPath(expectedJSON);
        JsonPath actual = new JsonPath(actualJSON);

        // Use JsonPath to extract and compare values
        assertEquals(expected.get("name"), actual.get("name"));
        assertEquals(expected.get("age"), actual.get("age"));
    }

    @Test
    public void testDifferentJSONs() {
        String expectedJSON = "{\"name\": \"John\", \"age\": 30}";
        String actualJSON = "{\"name\": \"Alice\", \"age\": 25}";

        JsonPath expected = new JsonPath(expectedJSON);
        JsonPath actual = new JsonPath(actualJSON);

        // Use JsonPath to extract and compare values
        assertNotEquals(expected.get("name"), actual.get("name"));
        assertNotEquals(expected.get("age"), actual.get("age"));
    }
}
