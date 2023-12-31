package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    public static String fileToString(String path) {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
