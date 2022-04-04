package FxmlStaff;

import java.io.IOException;
import java.sql.SQLException;

import Fxml.AlertHelper;
import Home.FileSystem;
import Objects.Patient;
import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NewPatientController {

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtID;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnBack;

	private Stage stage;

	private Scene scene;

	private String permission;

	@FXML
	void Back(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlStaff/DoctorMainPage.fxml"));

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		pane.getChildren().add(loader.load());

		DoctorMainPageController main = loader.getController();
		main.getPermission(permission);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(pane);
		stage.setTitle("Doctor Main Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void Save(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

		if (txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtID.getText().equals("")) {
			Window owner = btnSave.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must fill all the fields");
			return;
		}

		else {

			Patient patient = new Patient(txtFirstName.getText(), txtLastName.getText(), txtID.getText());

			int y = ConnectionClass.ExistPatient(patient.getID());

			if (y == 0) {
				int x = ConnectionClass.InsertPatientData(patient);

				FileSystem fileSystem = FileSystem.getInstance();
				fileSystem.initFile(patient.getID());

				if (x == 1) {
					Window owner = btnSave.getScene().getWindow();
					AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Done. ", "patient data saved");
					return;
				}
			} else {
				Window owner = btnSave.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Done. ", "patient already in the system");
				return;
			}

		}

	}

	public void getPermission(String per) {
		permission = per;
	}
}
