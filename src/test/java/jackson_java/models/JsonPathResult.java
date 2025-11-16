package jackson_java.models;
import com.fasterxml.jackson.databind.JsonNode;

public  class JsonPathResult {
    public JsonNode node;       // הצומת עצמו שאליו הגענו
    public JsonNode parent;     // האב של הצומת
    public String fieldName;    // שם השדה
    public Integer arrayIndex;  // אינדקס

}