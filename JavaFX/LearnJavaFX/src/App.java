import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Caminho para o arquivo de fonte Roboto
        String fontPath = "C:/Github/Java/JavaFX/LearnJavaFX/fonts/static/Quicksand-Regular.ttf";

        // Carregar a fonte personalizada a partir do arquivo
        Font.loadFont(getClass().getResourceAsStream(fontPath), 12);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Text scenetitle = new Text("Welcome to NuPPGIN");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 32));

        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), scenetitle);
        transition.setToX(0); // Define a nova posição horizontal do texto (deslocamento de 100 pixels para a
                              // direita)
        transition.setToY(20); // Define a nova posição vertical do texto (deslocamento de 100 pixels para
                               // baixo)
        transition.setFromX(0); // Define a posição inicial horizontal do texto
        transition.setFromY(-10); // Define a posição inicial vertical do texto
        transition.setCycleCount(1); // Executa a transição uma vez
        transition.setAutoReverse(false); // Não faz a transição de volta para a posição inicial
        transition.play();

        scenetitle.setOpacity(0.0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), scenetitle);
        fadeTransition.setToValue(1);
        fadeTransition.setFromValue(0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();

        scenetitle.setId("title");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        userName.setFont(new Font(16));
        grid.add(userName, 0, 2);

        TextField userTextField = new TextField();
        userTextField.setPrefSize(244, 32);
        grid.add(userTextField, 1, 2);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Sign in button is pressed!");
            }
        });

        confirmButton.setId("btn");
        // confirmButton.getStyleClass().add("btn");

        HBox hbBox = new HBox(10);
        hbBox.setAlignment(Pos.BOTTOM_RIGHT);
        hbBox.getChildren().add(confirmButton);
        grid.add(hbBox, 1, 5);

        Label pw = new Label("Password:");
        pw.setFont(new Font(16));
        grid.add(pw, 0, 3);

        PasswordField pwBox = new PasswordField();
        pwBox.setPrefSize(244, 32);
        grid.add(pwBox, 1, 3);

        scene.getStylesheets().add(getClass().getResource("styles/styles.css").toExternalForm());

        primaryStage.show();
    }
}
