package api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static api.Utils.requests.getRequest;
import static api.data.Url.Base_Url;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;

public class GET {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Base_Url;
    }

    @Test
    public void GetAllProducts() throws Exception {
        Response response = getRequest("/products");
        response.then() //מטפל בתגובה ומאפשר אימות
                .assertThat()
                .statusCode(200)
                .body("products", everyItem(hasKey("id")))
                .extract();


        System.out.println(response.asString());
    }

    @Test
    public void getSingleProduct() throws Exception {
        Response response = getRequest("/products/3");
        response.then() //מטפל בתגובה ומאפשר אימות
                .assertThat()
                .statusCode(200)
                .body("title", equalTo("Powder Canister"))
                .body("$", hasKey("id"))
                .extract();


        System.out.println(response.asString());
    }


    @Test
    public void getProductsCategories() throws Exception {
        Response response = getRequest("/products/categories");
        response.then() //מטפל בתגובה ומאפשר אימות
                .assertThat()
                .statusCode(200)
                .body("", everyItem(hasKey("slug")))
                .extract();


        System.out.println(response.asString());
    }

}
