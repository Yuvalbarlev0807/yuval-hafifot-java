package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static api.Utils.requests.deleteRequest;
import static api.data.Url.Base_Url;
import static org.hamcrest.Matchers.equalTo;

public class DELETE {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Base_Url;
    }

    @Test
    public void deleteProduct() throws Exception {
        Response response = deleteRequest("Content-Type", "application/json", "/products/1");
        response.then()
                .assertThat()
                .statusCode(200)
                .body("id" ,equalTo(1))
                .body("isDeleted" ,equalTo(true))
                .extract();


        System.out.println(response.asString());
    }
}