package jackson_java.testData;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.List;
import java.util.Map;
import static jackson_java.logics.actionsOverJson.mapper;

public class data{
    public static List<Map<String, Object>> yuvalArray = List.of(
            Map.of("key1", "value1"),
            Map.of("key2", "value2")
    );
    public static ArrayNode stringArray = mapper.valueToTree(List.of("red", "green", "blue"));
}
