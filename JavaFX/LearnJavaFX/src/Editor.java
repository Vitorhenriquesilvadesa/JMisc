import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Editor extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Font.loadFont(getClass().getResourceAsStream("fonts/coding/static/RobotoMono-Regular.ttf"), 14);

        BorderPane pane = new BorderPane();

        HBox toolBar = new HBox();
        toolBar.setPrefSize(1920, 64);
        toolBar.setMaxSize(1920, 128);
        toolBar.setId("tool-bar");
        pane.setTop(toolBar);

        VBox leftMenu = new VBox();
        leftMenu.setPrefSize(256, 600);
        leftMenu.setMaxSize(400, 1080);
        leftMenu.setId("left-menu");
        pane.setLeft(leftMenu);

        TextArea coding = new TextArea();
        coding.setWrapText(true);
        coding.setId("coding");
        coding.setFont(Font.font("Roboto Mono Regular", 14));
        pane.setCenter(coding);

        Scene scene = new Scene(pane, 800, 600);

        scene.getStylesheets().add(getClass().getResource("styles/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
