package rawcode;

import org.apache.commons.io.IOUtils;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Assists in reading
 */
public class RawcodeBinaryReader {

    /**
     * Reads a number of characters as a String
     *
     * @param stream    Byte stream
     * @param length    Character count to read
     * @return          String data
     */
    private String readString(ByteBuffer stream, int length) throws IOException {
        byte[] collected = new byte[length];
        for(int i = 0; i < length; i++) {
            collected[i] = readByte(stream);
        }
        return new String(collected);
    }

    /**
     * Reads a String until it finds a null terminator.
     *
     * @param stream    Byte stream
     * @return          String data
     */
    private String readString(ByteBuffer stream) throws IOException {
        List<Byte> bytes = new ArrayList<>();
        byte current = 0x00;
        do {
            current = readByte(stream);
            if(current != 0x00) {
                bytes.add(current);
            }
        } while(current != 0x00);
        byte[] bytesArray = new byte[bytes.size()];
        for(int i = 0; i < bytes.size(); i++) {
            bytesArray[i] = bytes.get(i);
        }
        return new String(bytesArray);
    }

    /**
     * Reads a single byte.
     *
     * @param stream    Byte stream
     * @return          1 byte
     */
    private byte readByte(ByteBuffer stream) throws IOException {
        return stream.get();
    }

    /**
     * Reads a 4 byte Unsigned Int.
     * Little endian.
     *
     * @param stream    Byte stream
     * @return          Uin32
     */
    private int readInt(ByteBuffer stream)  throws IOException {
        byte[] collected = new byte[4];
        for(int i = 0 ; i < 4; i++) {
            collected[i] = readByte(stream);
        }
        return java.nio.ByteBuffer.wrap(collected).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
    }

    /**
     * Reads a 4-byte Real.
     * Little endian.
     *
     * @param stream    Byte stream
     * @return          Real value
     */
    private double readReal(ByteBuffer stream) throws IOException {
        byte[] collected = new byte[4];
        for(int i = 0 ; i < 4; i++) {
            collected[i] = readByte(stream);
        }
        return ByteBuffer.wrap(collected).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    /**
     * Reads the simple header of the objects file.
     *
     * @param stream    Byte stream
     * @return          File header
     */
    public ObjectsHeader readHeader(ByteBuffer stream) throws IOException {
        ObjectsHeader header = new ObjectsHeader();
        header.setVersionId(readInt(stream));
        return header;
    }

    /**
     * Reads a Field from a ModifiedObject entity. Which is one of the specific World Editor
     * fields that was modified.
     *
     * @param stream    Byte stream
     * @return          Modified Field
     */
    private ModifiedObjectField readModifiedObjectField(ByteBuffer stream) throws IOException {
        ModifiedObjectField objectField = new ModifiedObjectField();
        objectField.setFieldCode(readString(stream, 4));
        objectField.setType(readInt(stream));
        if(objectField.getType() == W3TTypes.INT_TYPE) {
            objectField.setFieldData(readInt(stream)+"");
        } else if(objectField.getType() == W3TTypes.REAL_TYPE) {
            objectField.setFieldData(readReal(stream)+"");
        } else if(objectField.getType() == W3TTypes.UREAL_TYPE) {
            objectField.setFieldData(readReal(stream)+"");
        } else if(objectField.getType() == W3TTypes.STRING_TYPE) {
            objectField.setFieldData(readString(stream));
        } else {
            System.out.println("Ignored: " + objectField.getType());
        }

        objectField.setRawcode(readString(stream, 4));
        return objectField;
    }

    /**
     * Reads a ModifiedObject entity - which is a predefined wc3 object that was modified by maker.
     *
     * @param stream    Byte stream
     * @return          Modified Object entity
     */
    private ModifiedObject readModifiedObject(ByteBuffer stream) throws IOException {
        ModifiedObject modifiedObject = new ModifiedObject();
        modifiedObject.setRawcode(readString(stream, 4));
        modifiedObject.setUnknown(readInt(stream));
        modifiedObject.setModifiedFieldsCount(readInt(stream));
        for(int i = 0; i < modifiedObject.getModifiedFieldsCount(); i++) {
            modifiedObject.addField(readModifiedObjectField(stream));
        }
        return modifiedObject;
    }

    /**
     * Reads a Modified Field on a NewObject structure.
     *
     * @param stream    Byte stream
     * @return          Modified field entity
     */
    private NewObjectField readNewObjectField(ByteBuffer stream) throws IOException {
        NewObjectField objectField = new NewObjectField();
        objectField.setFieldCode(readString(stream, 4));
        objectField.setType(readInt(stream));
        if(objectField.getType() == W3TTypes.INT_TYPE) {
            objectField.setFieldData(readInt(stream)+"");
        } else if(objectField.getType() == W3TTypes.REAL_TYPE) {
            objectField.setFieldData(readReal(stream)+"");
        } else if(objectField.getType() == W3TTypes.UREAL_TYPE) {
            objectField.setFieldData(readReal(stream)+"");
        } else if(objectField.getType() == W3TTypes.STRING_TYPE) {
            objectField.setFieldData(readString(stream));
        }

        objectField.setRawcode(readString(stream, 4));
        return objectField;
    }

    /**
     * Reads the NewObject structure - which is a new object created by map maker.
     *
     * @param stream    Byte stream
     * @return          Object entity
     */
    private NewObject readNewObject(ByteBuffer stream) throws IOException {
        NewObject newObject = new NewObject();
        newObject.setOriginalRawcode(readString(stream, 4));
        newObject.setRawcode(readString(stream, 4));
        newObject.setModifiedFieldsCount(readInt(stream));
        for(int i = 0; i < newObject.getModifiedFieldsCount(); i++) {
            newObject.addField(readNewObjectField(stream));
        }
        return newObject;
    }

    /**
     * Reads bytes from a war3map.w3u/t file and returns them as an ObjectsStructure.
     *
     * @param input File linked to WC3 objects map.
     * @return      Objects Structure read.
     */
    public ObjectsStructure readObject(File input) {
        try {
            byte[] fileData = IOUtils.toByteArray(new FileInputStream(input));
            ByteBuffer stream = ByteBuffer.allocate(fileData.length);
            stream.put(fileData);
            stream.order(ByteOrder.LITTLE_ENDIAN);
            stream.flip();
            return readObject(stream);
        } catch (IOException ex) {
            throw new RuntimeException("File does not exist: " + input.getName());
        }
    }

    /**
     * Helper methos to read the full Objects entity.
     *
     * @param stream    Byte stream to read with
     * @return          Entity read
     */
    private ObjectsStructure readObject(ByteBuffer stream) {
        try {
            ObjectsStructure object = new ObjectsStructure();
            ObjectsMetadata metadata = new ObjectsMetadata();

            object.setHeader(readHeader(stream));
            metadata.setNumModifiedObjects(readInt(stream));
            for(int i = 0; i < metadata.getNumModifiedObjects(); i++) {
                object.addModifiedObject(readModifiedObject(stream));
            }
            metadata.setNumNewObjects(readInt(stream));
            for(int i = 0; i < metadata.getNumNewObjects(); i++) {
                object.addNewObject(readNewObject(stream));
            }
            return object;
        } catch (IOException ex) {
            throw new RuntimeException("Failure to parse w3t file: " + ex.getMessage());
        }
    }
}
