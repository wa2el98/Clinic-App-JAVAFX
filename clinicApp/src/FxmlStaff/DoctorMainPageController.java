package FxmlStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import Fxml.AlertHelper;
import javafx.event.ActionEvent;

public class DoctorMainPageController {

	@FXML
	private Button btnBack;

	@FXML
	private Button btnNewPatient;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnNewFile;

	@FXML
	private Button btnSignOut;

	private Stage stage;

	private Scene scene;

	private String permission;

	@FXML
	void Back(ActionEvent event) throws Exception {

		System.out.println("\n\n" + permission + "\n\n");

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
	void SignOut(ActionEvent event) throws Exception {
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
	void NewPatient(ActionEvent event) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlStaff/NewPatient.fxml"));

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		pane.getChildren().add(loader.load());

		NewPatientController main = loader.getController();
		main.getPermission(permission);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(pane);
		stage.setTitle("New Patient Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void NewFile(ActionEvent event) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlStaff/NewFile.fxml"));

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		pane.getChildren().add(loader.load());

		NewFileController main = loader.getController();
		main.getPermission(permission);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(pane);
		stage.setTitle("New File Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void Search(ActionEvent event) throws Exception {

		// System.out.println("\n" + permission);

		if (permission.equals("A")) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlStaff/SearchPatient.fxml"));

			StackPane pane = new StackPane();
			pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
					+ "-fx-background-size: 100% 100%;");

			pane.getChildren().add(loader.load());

			SearchPatientController main = loader.getController();
			main.getPermission(permission);

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(pane);
			stage.setTitle("Search Patient Page");
			stage.setScene(scene);
			stage.show();

		} else {
			Window owner = btnSearch.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Sorry, you dont have a permission");
			return;

		}
	}

	public void getPermission(String per) {
		permission = per;
	}

}
