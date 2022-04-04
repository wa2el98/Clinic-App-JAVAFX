package Fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class WelcomePageController {

	@FXML
	private Button StaffSignInBtn;

	@FXML
	private Button ManagerSignInBtn;

	@FXML

	private Stage stage;

	private Scene scene;

	@FXML
	void getStaffSignInBtn(ActionEvent event) throws Exception {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlStaff/StaffSignInPage.fxml"));
		pane.getChildren().add(newLoadedPane);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(pane);

		stage.setTitle("Staff Sign In Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void getManagerSignInBtn(ActionEvent event) throws Exception {

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

}