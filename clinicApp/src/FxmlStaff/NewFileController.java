package FxmlStaff;

import java.io.IOException;
import java.sql.SQLException;

import E_ART.Encryption;
import E_ART.Key;
import Fxml.AlertHelper;
import Home.FileSystem;
import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NewFileController {

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtTitle;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnBack;

	@FXML
	private TextArea txtInfo;

	private Stage stage;

	private Scene scene;

	private int x;

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

		if (txtID.getText().equals("") || txtInfo.getText().equals("") || txtTitle.getText().equals("")) {
			Window owner = btnSave.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must fill all the fields");
			return;
		}

		else

		{

			x = ConnectionClass.ExistPatient(txtID.getText());

			if (x == 1) {

				String Id = txtID.getText();
				String info = txtInfo.getText();
				String title = txtTitle.getText();

				/* encrypting info */

				String[] key = ConnectionClass.getNewKey();

				int N = Integer.parseInt(key[2].toString());
				int Variance = Integer.parseInt(key[3].toString());

				Key myKey = new Key(N, Variance);
				String encrypted_text = Encryption.encrypt(myKey, info);

				/* encrypting info */

				String firstfileName = ConnectionClass.getDirectory();
				String path = (firstfileName + "\\" + Id + "\\" + title + ".txt");

				FileSystem fileSystem = FileSystem.getInstance();
				fileSystem.insertInFile(path, encrypted_text);

				Window owner = btnSave.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Done. ", "patient info saved");
				return;

			}

			else {
				Window owner = btnSave.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
						"patient doesnt exist in the system, Add him before");
				return;
			}
		}

	}

	public void getPermission(String per) {
		permission = per;
	}

}
