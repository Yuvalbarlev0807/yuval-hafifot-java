package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static api.Utils.requests.putRequest;
import static api.data.RequestsBody.PutRequestBody;
import static api.data.Url.Base_Url;
import static org.hamcrest.Matchers.equalTo;


public class PUT {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Base_Url;
    }

    @Test
    public void changeProductName() throws Exception {
        Response response = putRequest("Content-Type", "application/json", PutRequestBody, "/products/1");
        response.then()
                .assertThat()
                .statusCode(200)
                .body("title" ,equalTo("iPhone Galaxy +1"))
                .extract();


        System.out.println(response.asString());
    }
}