package rawcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModifiedObject {

    private String rawcode;
    private int unknown;
    private int modifiedFieldsCount;
    private List<ModifiedObjectField> fields;

    public ModifiedObject() {
        this.fields = new ArrayList<>();
    }

    public String getRawcode() {
        return rawcode;
    }

    public void setRawcode(String rawcode) {
        this.rawcode = rawcode;
    }

    public int getUnknown() {
        return unknown;
    }

    public void setUnknown(int unknown) {
        this.unknown = unknown;
    }

    public int getModifiedFieldsCount() {
        return modifiedFieldsCount;
    }

    public void setModifiedFieldsCount(int modifiedFieldsCount) {
        this.modifiedFieldsCount = modifiedFieldsCount;
    }

    public List<ModifiedObjectField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public void addField(ModifiedObjectField field) {
        this.fields.add(field);
    }

}
