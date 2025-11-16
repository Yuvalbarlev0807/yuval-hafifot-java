package jackson_java.logics;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jackson_java.models.JsonPathResult;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import static jackson_java.logics.helpFunctions.*;

public class actionsOverJson {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static String renameField(File json, String path, String newFieldName) throws Exception {
        JsonNode rootNode = mapper.readTree(json);
        ObjectNode rootObject = (ObjectNode) rootNode;
        JsonPathResult result = getNodeByPathDetailed(rootObject, path);

        if (result.parent.isObject()) {
            ObjectNode parentObj = (ObjectNode) result.parent;
            JsonNode value = parentObj.get(result.fieldName);
            parentObj.remove(result.fieldName);
            parentObj.set(newFieldName, value);

        } else if (result.parent.isArray()) {
            renameArrayField(result, newFieldName);
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }

    public static String changeValue(File json, String path, Object newValue) throws Exception {
        JsonNode rootNode = mapper.readTree(json);
        ObjectNode rootObject = (ObjectNode) rootNode;
        JsonPathResult result = getNodeByPathDetailed(rootObject, path);
        JsonNode newValueNode = mapper.valueToTree(newValue);

        if (result.parent.isArray()) {
            ArrayNode arrayNode = (ArrayNode) result.parent;
            arrayNode.set(result.arrayIndex, newValueNode);
        } else if (result.parent.isObject()) {
            ObjectNode objNode = (ObjectNode) result.parent;
            objNode.set(result.fieldName, newValueNode);
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootObject);
    }

    public static boolean doesFieldHaveValue(File jsonFile, String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonFile);
        JsonPathResult result = getNodeByPathDetailed(root, path);
        JsonNode node = result.node;

        if (node == null || node.isMissingNode() || node.isNull()) {
            return false;
        }

        if (node.isTextual() && node.asText().trim().isEmpty()) {
            return false;
        }

        if (node.isArray() && node.isEmpty()) {
            return false;
        }

        if (node.isObject() && node.isEmpty()) {
            return false;
        }
        return true;
    }

    public static String deleteField(File json, String path) throws Exception {
        JsonNode rootNode = mapper.readTree(json);
        ObjectNode rootObject = (ObjectNode) rootNode;
        JsonPathResult result = getNodeByPathDetailed(rootObject, path);

        if (result.parent.isArray()) {
            ArrayNode arrayNode = (ArrayNode) result.parent;
            arrayNode.remove(result.arrayIndex);
        } else if (result.parent.isObject()) {
            ObjectNode objNode = (ObjectNode) result.parent;
            objNode.remove(result.fieldName);
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }

    public static String addField(File json, String path, String fieldName, Object fieldValue) throws Exception {
        JsonNode rootNode = mapper.readTree(json);
        ObjectNode rootObject = (ObjectNode) rootNode;

        JsonPathResult result = getNodeByPathDetailed(rootObject, path);
        JsonNode targetNode = result.node;

        // המרה חכמה של הערך לכל סוג אפשרי
        JsonNode valueNode = mapper.valueToTree(fieldValue);

        if (targetNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) targetNode;
            ObjectNode newObject = mapper.createObjectNode();
            newObject.set(fieldName, valueNode);
            arrayNode.add(newObject);
        } else if (targetNode.isObject()) {
            ObjectNode objNode = (ObjectNode) targetNode;
            objNode.set(fieldName, valueNode);
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }

    public static boolean doesFieldExists(File json, String fieldName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        return fieldExistsRecursive(rootNode, fieldName);
    }

    private static boolean fieldExistsRecursive(JsonNode node, String fieldName) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            if (objectNode.has(fieldName)) {
                return true;
            }

            Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                if (fieldExistsRecursive(entry.getValue(), fieldName)) {
                    return true;
                }
            }
        } else if (node.isArray()) {
            for (JsonNode element : node) {
                if (fieldExistsRecursive(element, fieldName)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static String extracDataFromField(File json, String path) throws Exception {
        JsonNode rootNode = mapper.readTree(json);
        ObjectNode rootObject = (ObjectNode) rootNode;
        JsonPathResult result = getNodeByPathDetailed(rootObject, path);
        JsonNode Extractedvalue = null;

        if (result.parent.isObject()) {
            Extractedvalue = extractFromObject(result);

        } else if (result.parent.isArray()) {
            Extractedvalue = extractFromArray(result);
        }
        return (Extractedvalue.toString());
    }


}