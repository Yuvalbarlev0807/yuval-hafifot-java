package jackson_java.convertJsonToObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import jackson_java.convertJsonToObject.classes.Product;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product= mapper.readValue(new File("src/smallerJson.json"),Product.class);
        System.out.println(product.specifications.cpu);
        System.out.println(product.variants.get(0).price);
        System.out.println(product.tags.get(0));
    }
}