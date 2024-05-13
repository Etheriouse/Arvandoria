import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer une étiquette avec un texte
        Label label = new Label("Bonjour, monde !");

        // Créer un conteneur de mise en page StackPane et ajouter l'étiquette
        StackPane root = new StackPane();
        root.getChildren().add(label);

        // Créer une scène avec le conteneur de mise en page
        Scene scene = new Scene(root, 300, 200);

        // Définir la scène sur la fenêtre principale (Stage)
        primaryStage.setScene(scene);

        // Définir le titre de la fenêtre
        primaryStage.setTitle("Ma première application JavaFX");

        // Afficher la fenêtre principale
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Lancer l'application JavaFX
        launch(args);
    }
}