package FxmlManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import E_ART.Encryption;
import E_ART.Key;
import Fxml.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import Home.FileSystem;
import connectivity.ConnectionClass;

public class EncryptAllController {

	@FXML
	private TextField txtDirectory;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnEncrypt;

	private Stage stage;

	private Scene scene;

	@FXML
	void Back(ActionEvent event) throws IOException {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlManager/ManagerMainPage.fxml"));
		pane.getChildren().add(newLoadedPane);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(pane);

		stage.setTitle("Manager Main Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void Encrypt(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (txtDirectory.getText().equals("")) {
			Window owner = btnEncrypt.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must fill the field 'Directory'");
			return;
		}

		else {
			String path;
			String directory = txtDirectory.getText();

			List<File> files = FileSystem.listf(directory);
			System.out.println(files.toString());

			for (int i = 0; i < files.size(); i++) {

				path = files.get(i).getAbsolutePath();

				String extension = "";

				int index = path.lastIndexOf('.');
				if (index > 0) {
					extension = path.substring(index + 1);
				}

				if (extension.equals("txt")) {
					// reading file content
					String info = FileSystem.extractFile(path);

					// delete file content
					File f = new File(path);
					PrintWriter writer = new PrintWriter(f);
					writer.print("");
					writer.close();

					/* encrypting info */

					String[] key = ConnectionClass.getNewKey();

					int N = Integer.parseInt(key[2].toString());
					int Variance = Integer.parseInt(key[3].toString());

					Key myKey = new Key(N, Variance);
					String encrypted_text = Encryption.encrypt(myKey, info);

					// writing back to file
					FileSystem fileSystem = FileSystem.getInstance();
					fileSystem.insertInFile(path, encrypted_text);
				}

			}

			Window owner = btnEncrypt.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Done. ", "All Files Encrypted");

			// return back to manager main page

			StackPane pane = new StackPane();
			pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
					+ "-fx-background-size: 100% 100%;");

			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlManager/ManagerMainPage.fxml"));
			pane.getChildren().add(newLoadedPane);

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			scene = new Scene(pane);

			stage.setTitle("Manager Main Page");
			stage.setScene(scene);
			stage.show();

		}

	}

}
