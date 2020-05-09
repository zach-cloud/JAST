package interfaces;

import java.util.Map;

public interface ICFGService {

    /**
     * Reads the Configuration file and returns it as a new Map.
     * If it doesn't exist, makes a new CFG Reader.
     *
     * @param cfgPath   Path to config file
     * @return CFG Map
     */
    Map<String, String> readConfigFile(String cfgPath);

    /**
     * Reads the Configuration file and returns it as a new Map.
     * If it doesn't exist, makes a new CFG Reader.
     *
     * @param cfgPath   Path to config file
     * @param cfgMap    Map to write out
     * @return CFG Map
     */
    void writeConfigFile(String cfgPath, Map<String,String> cfgMap);
}
