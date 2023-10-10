import io.restassured.RestAssured;

public class DeleteItem {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://todo.ly/API/";

        RestAssured.when()
                .delete("/Items/123.json")
                .then()
                .statusCode(200);
    }
}
