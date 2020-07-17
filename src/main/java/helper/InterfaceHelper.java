package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterfaceHelper {

    public static String executeCommand(String command) throws IOException {
        Runtime rt = Runtime.getRuntime();
        System.out.println("Sending command: " + command);
        Process process = rt.exec(command);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));

        StringBuilder result = new StringBuilder();

        addStreamToStringBuilder(stdInput, result);
        addStreamToStringBuilder(stdError, result);

        return result.toString();
    }

    private static void addStreamToStringBuilder(BufferedReader stream, StringBuilder result) throws IOException {
        String s = null;
        while ((s = stream.readLine()) != null) {
            result.append(s).append("\n");
        }
    }
}
