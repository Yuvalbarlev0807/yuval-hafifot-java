package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static api.Utils.requests.postRequest;
import static api.Utils.utilsFunctions.extractResponse;
import static api.data.RequestsBody.PostRequestBody;
import static api.data.Url.Base_Url;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.junit.jupiter.api.BeforeAll;

public class POST {


    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Base_Url;
    }

    @Test
    public void addProduct() throws Exception {

        Response response = postRequest("Content-Type", "application/json", PostRequestBody, "/products/add");
        response.then()
                .assertThat()
                .statusCode(201)
                .body("title",equalTo("BMW Pencil"))
                .body("$",hasKey("id"))
                .extract();


        System.out.println(response .asString());
        System.out.println(extractResponse(response));

    }
}