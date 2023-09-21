package org.vtko.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileStream {

    private String relativePath = System.getProperty("user.dir") + "/src/org/vtko/voxelfiles/";
    private String path;

    public FileStream(String path) {
        this.path = relativePath + path;
    }

    public String getString() throws FileNotFoundException, IOException {
        return this.parseFileContent();
    }

    private String parseFileContent() throws FileNotFoundException, IOException {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }

        return sb.toString();
    }
}
