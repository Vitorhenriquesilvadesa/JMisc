import org.vtko.parser.HTMLPage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static String workingDir = System.getProperty("user.dir") + "/src/";

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(workingDir + "res/index.html"))) {
            String line;

            while((line = reader.readLine()) != null){
                sb.append(line);
            }

            HTMLPage page = new HTMLPage(sb.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}