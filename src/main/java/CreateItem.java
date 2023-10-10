import io.restassured.*;
import io.restassured.http.ContentType;

public class CreateItem {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://todo.ly/API/";

        String requestBody = "{\"Content\": \"Nueva tarea\", \"ProjectId\": 123}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/Items.json")
                .then()
                .statusCode(200);
    }
}
