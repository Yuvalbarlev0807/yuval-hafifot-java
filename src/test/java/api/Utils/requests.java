package api.Utils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class requests {

    public static Response getRequest( String path) throws Exception {
        return given()
                .when()
                .get(path);
    }

    public static Response postRequest(String headerName, String headerValue, String body, String path) throws Exception {
        return given()
                .header(headerName, headerValue)
                .body(body)
                .when()
                .post(path);
    }

    public static Response putRequest(String headerName, String headerValue, String body, String path) throws Exception {
        return given()
                .header(headerName, headerValue)
                .body(body)
                .when()
                .put(path);
    }

    public static Response deleteRequest(String headerName, String headerValue, String path) throws Exception {
        return given()
                .header(headerName, headerValue)
                .when()
                .delete(path);
    }
}