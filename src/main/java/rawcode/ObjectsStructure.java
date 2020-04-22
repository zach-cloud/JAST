package rawcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectsStructure {

    private ObjectsHeader header;
    private List<ModifiedObject> modifiedObjects;
    private List<NewObject> newObjects;

    public ObjectsStructure() {
        this.modifiedObjects = new ArrayList<>();
        this.newObjects = new ArrayList<>();
    }

    public ObjectsHeader getHeader() {
        return header;
    }

    public void setHeader(ObjectsHeader header) {
        this.header = header;
    }

    public List<ModifiedObject> getModifiedObjects() {
        return Collections.unmodifiableList(modifiedObjects);
    }

    public List<NewObject> getNewObjects() {
        return Collections.unmodifiableList(newObjects);
    }

    public void addModifiedObject(ModifiedObject object) {
        modifiedObjects.add(object);
    }

    public void addNewObject(NewObject object) {
        newObjects.add(object);
    }
}
