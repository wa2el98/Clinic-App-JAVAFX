package FxmlManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.event.ActionEvent;

public class ManagerMainPageController {

	@FXML
	private Button btnBack;

	@FXML
	private Button btnEncrypt;

	@FXML
	private Button btnDecrypt;

	@FXML
	private Button btnChangeKeyValue;

	@FXML
	private Button btnSignOut;

	private Stage stage;

	private Scene scene;

	@FXML
	void Back(ActionEvent event) throws IOException {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlManager/ManagerSignInPage.fxml"));
		pane.getChildren().add(newLoadedPane);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(pane);

		stage.setTitle("Manager Sign In Page");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void SignOut(ActionEvent event) throws IOException {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlManager/ManagerSignInPage.fxml"));
		pane.getChildren().add(newLoadedPane);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(pane);

		stage.setTitle("Manager Sign In Page");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void ChangeKeyValue(ActionEvent event) throws IOException {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlManager/ChangeKeyValue.fxml"));
		pane.getChildren().add(newLoadedPane);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(pane);

		stage.setTitle("Change Key Value Page");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void Encrypt(ActionEvent event) throws IOException {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlManager/EncryptAllPage.fxml"));
		pane.getChildren().add(newLoadedPane);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(pane);

		stage.setTitle("Encrypt All Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void Decrypt(ActionEvent event) throws IOException {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlManager/DecryptAllPage.fxml"));
		pane.getChildren().add(newLoadedPane);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(pane);

		stage.setTitle("Decrypt All Page");
		stage.setScene(scene);
		stage.show();

	}

}
