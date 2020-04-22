package rawcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewObject {

    private String originalRawcode;
    private String rawcode;
    private int modifiedFieldsCount;
    private List<NewObjectField> fields;


    public NewObject() {
        this.fields = new ArrayList<>();
    }

    public String getRawcode() {
        return rawcode;
    }

    public void setRawcode(String rawcode) {
        this.rawcode = rawcode;
    }

    public String getOriginalRawcode() {
        return originalRawcode;
    }

    public void setOriginalRawcode(String originalRawcode) {
        this.originalRawcode = originalRawcode;
    }

    public int getModifiedFieldsCount() {
        return modifiedFieldsCount;
    }

    public void setModifiedFieldsCount(int modifiedFieldsCount) {
        this.modifiedFieldsCount = modifiedFieldsCount;
    }

    public List<NewObjectField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public void addField(NewObjectField field) {
        this.fields.add(field);
    }
}
