import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Map<String, Integer> frequence = new HashMap<String, Integer>();
        List<String> list = new ArrayList<String>();

        String currentDir = System.getProperty("user.dir") + "/src/";
        System.out.println(currentDir + "source_text.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(currentDir + "source_text.txt")));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] line_splited = line.split(" ");

                for (String word : line_splited) {
                    if (!frequence.containsKey(word)) {
                        frequence.put(word, 1);
                        list.add(word);
                    } else {
                        frequence.replace(word, frequence.get(word) + 1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : list) {

            System.out.println(s + ": " + frequence.get(s));
        }
    }
}