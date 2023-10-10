import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UpdateItem {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://todo.ly/API/";

        String requestBody = "{\"Content\": \"Tarea actualizada\"}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/Items/123.json")
                .then()
                .statusCode(200);
    }
}
