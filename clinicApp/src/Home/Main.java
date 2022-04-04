package Home;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Fxml/WelcomePage.fxml"));
		pane.getChildren().add(newLoadedPane);

		Scene scene = new Scene(pane);

		primaryStage.setTitle("Welcome Page");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) throws IOException {
		launch(args);

	}

}