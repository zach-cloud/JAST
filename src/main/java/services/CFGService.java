package services;

import exception.ParsingException;
import interfaces.ICFGService;
import interfaces.IFileWriterService;
import interfaces.IOutputService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CFGService implements ICFGService {

    private IFileWriterService writerService;
    private IOutputService outputService;

    public CFGService() {
        this.writerService = new FileWriterService();
        this.outputService = new OutputService();
    }

    /**
     * Reads the Configuration file and returns it as a new Map.
     * If it doesn't exist, makes a new CFG Reader.
     *
     * @param cfgPath Path to config file
     * @return CFG Map
     */
    @Override
    public Map<String, String> readConfigFile(String cfgPath) {
        File file = new File(cfgPath);
        if(!file.exists()) {
            writeConfigFile(cfgPath, new HashMap<String,String>());
            return new HashMap<String,String>();
        }
        try {
            Scanner in = new Scanner(file);
            Map<String,String> returnMap = new HashMap<>();
            while(in.hasNextLine()) {
                String line = in.nextLine();
                if(!line.isEmpty()) {
                    if(line.contains(":")) {
                        String part1 = line.substring(0, line.indexOf(":"));
                        String part2 = line.substring(1+line.indexOf(":"));
                        returnMap.put(part1, part2);
                    }
                }
            }
            return returnMap;
        } catch (IOException ex) {
            throw new ParsingException("Cannot read file: " + file.getAbsolutePath());
        }
    }

    /**
     * Reads the Configuration file and returns it as a new Map.
     * If it doesn't exist, makes a new CFG Reader.
     *
     * @param cfgPath Path to config file
     * @param cfgMap  Map to write out
     * @return CFG Map
     */
    @Override
    public void writeConfigFile(String cfgPath, Map<String, String> cfgMap) {
        StringBuilder builder = new StringBuilder();
        for(String key : cfgMap.keySet()) {
            builder.append(key).append(":").append(cfgMap.get(key)).append("\n");
        }
        if(builder.length()>1) {
            builder.setLength(builder.length()-1);
        }
        writerService.writeString(builder.toString(), cfgPath);
    }
}
