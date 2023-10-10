import io.restassured.RestAssured;

public class ReadItem {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://todo.ly/API/";

        RestAssured.when()
                .get("/Items/123.json")
                .then()
                .statusCode(200);
    }
}
