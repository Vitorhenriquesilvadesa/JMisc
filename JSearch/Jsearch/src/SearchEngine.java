import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    public List<File> files = new ArrayList<File>();

    public SearchEngine() {
        this.files.add(new File("C:/Github/Java/JSearch/Jsearch/src/SearchEngine.java"));
        getMethods();
    }

    private void getMethods() {

        BufferedReader reader;
        String fileContent = "";

        for (File file : this.files) {
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    fileContent += line + "\n";
                }

                matchMethod(fileContent);

            } catch (FileNotFoundException e) {
                System.out.println("File not found: \"" + file.getName() + "\"");
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

    private String[] matchMethod(String source) {

        String words[] = source.split(" ");
        String methodSignature = "";

        for (int i = 0; i < words.length; i++) {

            String word = words[i];
            String formatedWord = word.replaceAll(" ", "").replaceAll("\t", "").replaceAll("\n", "");

            if (formatedWord.equals("public") || formatedWord.equals("protected") || formatedWord.equals("private")) {

                methodSignature += formatedWord + " ";

                for (int j = i + 1; j < words.length; j++) {
                    String otherWord = words[j];
                    String formatedOtherWord = "";

                    for (int k = 0; k < otherWord.length(); k++) {
                        if (otherWord.charAt(k) != ';' && otherWord.charAt(k) != '{' && otherWord.charAt(k) != '\n') {
                            formatedOtherWord += otherWord.charAt(k);
                        } else {
                            methodSignature += formatedOtherWord;
                            System.out.println(methodSignature);
                            methodSignature = "";
                            formatedOtherWord = "";
                            break;
                        }
                    }

                    formatedOtherWord = "";
                }
            }
        }

        return null;
    }
}