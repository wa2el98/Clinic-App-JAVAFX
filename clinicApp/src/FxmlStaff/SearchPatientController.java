package FxmlStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import E_ART.Decryption;
import E_ART.Key;
import Fxml.AlertHelper;
import Home.FileSystem;
import Objects.PatientFile;
import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SearchPatientController implements Initializable {

	@FXML
	private TextField txtID;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnOpen;

	@FXML
	private TableView<PatientFile> FilesTable;

	@FXML
	private TableColumn<PatientFile, String> FileName;

	@FXML
	private TableColumn<PatientFile, String> FileDate;

	private Stage stage;

	private Scene scene;

	private String permission;

	ObservableList<PatientFile> List = FXCollections.observableArrayList();

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
	void Search(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

		ArrayList<String> res;

		if (txtID.getText().equals("")) {
			Window owner = btnSearch.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must fill all the fields");
			return;
		}

		else {
			FileSystem file = FileSystem.getInstance();
			res = file.extractAction(txtID.getText());

			if (res == null) {
				Window owner = btnSearch.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Invalid Id");
				return;
			} else {

				// Initialize PatientFile ObserveList
				PatientFile p;

				int i = 0;

				FilesTable.getItems().clear();

				while (res.size() > i) {
					// action
					p = new PatientFile(res.get(i), res.get(i + 1));

					i += 2;

					List = FilesTable.getItems();
					List.add(p);
					FilesTable.setItems(List);
				}

			}

		}

	}

	@FXML
	void Open(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

		if (txtID.getText().equals("")) {
			Window owner = btnSearch.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must fill all the fields");
			return;
		}

		else {

			List = FilesTable.getSelectionModel().getSelectedItems();

			if (List.isEmpty()) {
				Window owner = btnOpen.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must choose a file.");
				return;
			} else {

				String title = List.get(0).getFileName();

				String res;

				String firstfileName = ConnectionClass.getDirectory();
				String path = (firstfileName + "\\" + txtID.getText() + "\\" + title);

				res = FileSystem.extractFile(path);

				/* decrypting res */
				String[] key = ConnectionClass.getNewKey();

				int N = Integer.parseInt(key[2].toString());
				int Variance = Integer.parseInt(key[3].toString());

				Key myKey = new Key(N, Variance);
				String info = Decryption.decrypt(myKey, res);

				System.out.println("\n\n");
				System.out.println(info.toCharArray());
				System.out.println("\n\n");

				/* decrypting res */

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlStaff/AskedFile.fxml"));

				StackPane pane = new StackPane();
				pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
						+ "-fx-background-size: 100% 100%;");

				pane.getChildren().add(loader.load());

				AskedFileController ask = loader.getController();

				StringBuilder str = new StringBuilder();
				int i, x;
				for (i = 0; i < info.length(); i++) {
					x = info.charAt(i);
					if (x == 127)
						str.append(" ");
					else
						str.append(info.charAt(i));
				}
				// System.out.print(str);

				info = str.toString();
				ask.getText(info);

				// ask=loader.getController();
				ask.getPermission(permission);

				ask.getPath(firstfileName + "\\" + txtID.getText() + "\\" + title);

				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(pane);
				stage.setTitle("Asked File Page");
				stage.setScene(scene);
				stage.show();

			}
		}

	}

	public void getPermission(String per) {
		permission = per;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		FileName.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("FileName"));
		FileDate.setCellValueFactory(new PropertyValueFactory<PatientFile, String>("FileDate"));

		FilesTable.setItems(List);

	}

}
