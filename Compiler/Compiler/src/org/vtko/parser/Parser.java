package org.vtko.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private String fileName;

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public List<Token> parse() {

        List<Token> tokens = new ArrayList<Token>();

        String fileContent;
        try {
            FileStream fs = new FileStream(this.fileName + ".vxl");
            fileContent = fs.getString();
            System.out.println(fileContent);
        } catch (IOException e) {
            System.out.println("Cannot find file");
        }

        return tokens;
    }
}
