package interfaces;

import rawcode.ObjectsStructure;

/**
 * Helper service to generate clean rawcode strings based on rawcode objects
 */
public interface IRawcodeService {

    /**
     * Adds a WTS File resource to replace Strings with
     *
     * @param wtsFile   WTS File
     */
    void addWTS(String wtsFile);

    /**
     * Creates a Rawcodes text string based on an Objects Structure
     *
     * @param objects   W3 Objects File
     * @return          Rawcodes text string
     */
    String makeRawcodesFrom(ObjectsStructure objects);

    /**
     * Creates a Rawcodes text string based on a file pointing to an Objects structure
     *
     * @param filePath  File path
     * @return          Rawcodes text string
     */
    String makeRawcodesFrom(String filePath);

}
