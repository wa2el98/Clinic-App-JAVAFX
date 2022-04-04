package FxmlManager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import E_ART.Decryption;
import E_ART.Encryption;
import E_ART.Key;
import Fxml.AlertHelper;
import Home.FileSystem;
import connectivity.ConnectionClass;
import javafx.event.ActionEvent;

public class ChangeKeyValueController {

	@FXML
	private TextField txtN;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnBack;

	@FXML
	private TextField txtVariance;

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
	void Save(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

		if (txtN.getText().equals("") || txtVariance.getText().equals("")) {
			Window owner = btnSave.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must fill all the fields");
			return;
		}

		else {

			int N, Variance;

			String[] dataNew = new String[2];
			dataNew[0] = txtN.getText();
			dataNew[1] = txtVariance.getText();

			String[] dataOld = new String[2];

			N = Integer.parseInt(txtN.getText());
			Variance = Integer.parseInt(txtVariance.getText());

			// get the key values before update
			String[] keyOld = ConnectionClass.getNewKey();

			// note: keyOld[0]=key keyOld[1]=new
			dataOld[0] = keyOld[2].toString();
			dataOld[1] = keyOld[3].toString();

			int NOld = Integer.parseInt(dataOld[0]);
			int VarianceOld = Integer.parseInt(dataOld[1]);

			int x = ConnectionClass.changeNewKeyValue(dataNew);

			int y = ConnectionClass.changeOldKeyValue(dataOld);

			// move on every file in directory

			String path;
			String directory = ConnectionClass.getDirectory();

			List<File> files = FileSystem.listf(directory);
			System.out.println(files.toString());

			for (int i = 0; i < files.size(); i++) {

				path = files.get(i).getAbsolutePath();

				String extension = "";
				int index = path.lastIndexOf('.');
				if (index > 0) {
					extension = path.substring(index + 1);
					// System.out.println(extension + "\n\n");
				}

				if (extension.equals("txt")) {
					// reading file content
					String info = FileSystem.extractFile(path);

					// delete file content
					File f = new File(path);
					PrintWriter writer = new PrintWriter(f);
					writer.print("");
					writer.close();

					/* decrypting info with Old keys */

					Key oldKey = new Key(NOld, VarianceOld);
					String decrypted_text = Encryption.encrypt(oldKey, info);

					/* encrypting info with new keys */

					Key newKey = new Key(N, Variance);
					String encrypted_text = Decryption.decrypt(newKey, decrypted_text);

					// writing back to file
					FileSystem fileSystem = FileSystem.getInstance();
					fileSystem.insertInFile(path, encrypted_text);
				}

			}

			if ((x == 1) && (y == 1)) {
				Window owner = btnSave.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Done. ", "Key Values Changed");
				return;
			}

			// return back to main page

			StackPane pane = new StackPane();
			pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
					+ "-fx-background-size: 100% 100%;");

			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/FxmlManager/ManagerMainPage.fxml"));
			pane.getChildren().add(newLoadedPane);

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			scene = new Scene(pane);

			stage.setTitle("Manager Sign In Page");
			stage.setScene(scene);
			stage.show();

		}

	}
}
