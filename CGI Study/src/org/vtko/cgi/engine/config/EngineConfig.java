package org.vtko.cgi.engine.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class EngineConfig {

    private static final String WIDTH_KEY = "width: ";
    private static final String HEIGHT_KEY = "height: ";
    private static final String TITLE_KEY = "title: ";
    private static int windowWidth;
    private static int windowHeight;
    private static String windowTitle;

    public static void init(String settingsDirectory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(settingsDirectory + "/" + "init_settings.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#WINDOW_SIZE")) {
                    readWindowSize(reader);
                } else if (line.startsWith("#WINDOW_TITLE")) {
                    readWindowTitle(reader);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readWindowSize(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).startsWith("#END_WINDOW_SIZE")) {
            if (line.startsWith(WIDTH_KEY)) {
                windowWidth = Integer.parseInt(line.substring(WIDTH_KEY.length()));
            } else if (line.startsWith(HEIGHT_KEY)) {
                windowHeight = Integer.parseInt(line.substring(HEIGHT_KEY.length()));
            }
        }
    }

    private static void readWindowTitle(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).startsWith("#END_WINDOW_TITLE")) {
            if (line.startsWith(TITLE_KEY)) {
                windowTitle = line.substring(TITLE_KEY.length() + 1, line.length() - 1);
            }
        }
    }

    public static WindowProps getWindowConfig() {
        return new WindowProps(windowWidth, windowHeight, windowTitle);
    }
}
