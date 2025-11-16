package jackson_java;
import org.junit.jupiter.api.Test;
import java.io.File;
import static jackson_java.logics.actionsOverJson.*;
import static jackson_java.logics.actionsOverJson.addField;
import static jackson_java.testData.data.stringArray;

public class jsonTests {
    File jsonFile = new File("src/test/java/jackson_java/jsonFile.json");

    @Test
    public void jsonTest() throws Exception {

String returnedValue = renameField(jsonFile, "variants[0].sku", "yuval");
String changedValue = changeValue(jsonFile, "inStock", "true");
String deletedFieldJson = deleteField(jsonFile, "features");
String extractedDate=extracDataFromField(jsonFile, "variants[1].sku");
String addedField=addField(jsonFile,".","yuval","ber-lev");
String addedArrayField=addField(jsonFile,".","yuval",stringArray);
Boolean doesFieldExists = doesFieldExists(jsonFile, "city");
Boolean doesFieldHasValue=doesFieldHaveValue(jsonFile,"variants[0]");
        System.out.println(addedField);
    }
            }