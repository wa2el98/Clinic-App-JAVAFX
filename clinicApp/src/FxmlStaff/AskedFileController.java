package FxmlStaff;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import E_ART.Encryption;
import E_ART.Key;
import Fxml.AlertHelper;
import Home.FileSystem;
import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AskedFileController {

	@FXML
	private Button btnBack;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;

	@FXML
	private TextArea txtInfo;

	private Stage stage;

	private Scene scene;

	private String permission;

	private String Path;

	// ,ID,Title;

	@FXML
	void Back(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlStaff/SearchPatient.fxml"));

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		pane.getChildren().add(loader.load());

		SearchPatientController ask = loader.getController();
		ask.getPermission(permission);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(pane);
		stage.setTitle("Search Patient Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void Delete(ActionEvent event) throws IOException {

		try {
			File f = new File(Path); // file to be delete
			if (f.delete()) // returns Boolean value
			{
				System.out.println(f.getName() + " deleted"); // getting and printing the file name
				Window owner = btnDelete.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Done. ", "File deleted successfully");
			} else {
				System.out.println("failed");
				Window owner = btnDelete.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Cant delete this file");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// return Back after deleting
		// Back();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlStaff/SearchPatient.fxml"));

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		pane.getChildren().add(loader.load());

		SearchPatientController ask = loader.getController();
		ask.getPermission(permission);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(pane);
		stage.setTitle("Search Patient Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void Edit(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

		if (txtInfo.getText().equals("")) {
			Window owner = btnEdit.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must fill the field");
			return;
		}

		else

		{

			// delete file content
			File f = new File(Path);
			PrintWriter writer = new PrintWriter(f);
			writer.print("");
			writer.close();

			String info = txtInfo.getText();

			/* encrypting info */

			String[] key = ConnectionClass.getNewKey();

			int N = Integer.parseInt(key[2].toString());
			int Variance = Integer.parseInt(key[3].toString());

			Key myKey = new Key(N, Variance);
			String encrypted_text = Encryption.encrypt(myKey, info);

			FileSystem fileSystem = FileSystem.getInstance();
			fileSystem.insertInFile(Path, encrypted_text);

			Window owner = btnEdit.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Done. ", "Edit saved");
			return;

		}

	}

	public void getPath(String path) {
		Path = path;
	}

	public void getText(String info) {
		txtInfo.setText(info);
	}

	public void getPermission(String per) {
		permission = per;
	}
}
