package jackson_java.logics;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jackson_java.models.JsonPathResult;

public class helpFunctions {

public static void renameArrayField(JsonPathResult result, String newFieldName){
    ArrayNode arrayNode = (ArrayNode) result.parent;
    JsonNode element = arrayNode.get(result.arrayIndex);
    if (element != null && element.isObject()) {
        ObjectNode elementObj = (ObjectNode) element;
        JsonNode value = elementObj.get(result.fieldName);
        elementObj.remove(result.fieldName);
        elementObj.set(newFieldName, value);
    }
}
    public static JsonNode extractFromObject(JsonPathResult result) {
        ObjectNode parentObj = (ObjectNode) result.parent;
        return parentObj.get(result.fieldName);
    }
    public static JsonNode extractFromArray(JsonPathResult result) {
        ArrayNode arrayNode = (ArrayNode) result.parent;

        if (result.fieldName == null || result.fieldName.isEmpty()) {
            return arrayNode.get(result.arrayIndex);
        } else {
            JsonNode element = arrayNode.get(result.arrayIndex);
            if (element != null && element.isObject()) {
                return element.get(result.fieldName);
            }
        }
        return null;
    }

    public static JsonPathResult getNodeByPathDetailed(JsonNode root, String path) {
        String[] parts = path.split("\\.");
        JsonNode current = root;
        JsonNode parent = null;
        String currentField = null;
        Integer currentIndex = null;

        for (String part : parts) {
            parent = current;
            currentIndex = null;

            // בדיקה אם זה שדה עם אינדקס, לדוגמה: variants[1]
            if (part.matches(".+\\[\\d+\\]")) {
                String fieldName = part.substring(0, part.indexOf('['));
                int index = Integer.parseInt(part.substring(part.indexOf('[') + 1, part.indexOf(']')));

                current = current.path(fieldName);

                parent = current;
                current = current.get(index);
                currentField = null;
                currentIndex = index;
            }
            // אחרת — שדה רגיל באובייקט
            else {
                current = current.path(part);
                currentField = part;
            }
        }

        JsonPathResult result = new JsonPathResult();
        result.node = current;
        result.parent = parent;
        result.fieldName = currentField;
        result.arrayIndex = currentIndex;
        return result;
    }


}