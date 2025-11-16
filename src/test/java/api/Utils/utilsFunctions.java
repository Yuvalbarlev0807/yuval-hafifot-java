package api.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class utilsFunctions{
    public static int extractResponse(Response response) throws Exception {
        String responseBody = response.asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);
        return jsonNode.get("id").asInt();
    }
}