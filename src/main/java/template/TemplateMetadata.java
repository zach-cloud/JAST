package template;

import java.util.ArrayList;
import java.util.List;

public final class TemplateMetadata {

    private List<TemplateField> fields;

    public TemplateMetadata() {
        this.fields = new ArrayList<>();
    }

    public void add(TemplateField field) {
        this.fields.add(field);
    }

    public List<TemplateField> getFields() {
        return fields;
    }

    public void setFields(List<TemplateField> fields) {
        this.fields = fields;
    }
}
