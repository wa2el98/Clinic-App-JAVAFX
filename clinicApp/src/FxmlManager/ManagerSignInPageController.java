package FxmlManager;

import java.io.IOException;

import Fxml.AlertHelper;
import Objects.Manager;
import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ManagerSignInPageController {

	@FXML
	private Pane frame;

	@FXML
	private Button btnBack;

	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtPassword;

	@FXML
	private PasswordField passwordHidden;

	@FXML
	private CheckBox checkBox;

	@FXML
	private Button btnLogIn;

	private Stage stage;

	private Scene scene;

	@FXML
	void ChangeVisibility(ActionEvent event) {
		if (checkBox.isSelected()) {
			txtPassword.setText(passwordHidden.getText());
			txtPassword.setVisible(true);
			passwordHidden.setVisible(false);
			return;
		}
		passwordHidden.setText(txtPassword.getText());
		passwordHidden.setVisible(true);
		txtPassword.setVisible(false);
	}

	@FXML
	void Back(ActionEvent event) throws Exception {

		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-image: url('Fxml/clinic.jpg');" + "-fx-background-repeat: no-repeat;"
				+ "-fx-background-size: 100% 100%;");

		Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Fxml/WelcomePage.fxml"));
		pane.getChildren().add(newLoadedPane);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(pane);

		stage.setTitle("Welcome Page");
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void LogIn(ActionEvent event) throws Exception {

		if (txtUserName.getText().equals("") || passwordHidden.getText().equals("")) {
			Window owner = btnLogIn.getScene().getWindow();
			AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "You must fill all the fields");
			return;
		}

		else {

			String[] data = new String[2];
			data[0] = txtUserName.getText();

			Integer pass = passwordHidden.getText().hashCode();

			data[1] = pass.toString();

			Manager manager = ConnectionClass.ManagerCheckLogInData(data);

			if (!(manager.getID().equals("null"))) {

				try {

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
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			else {
				Window owner = btnLogIn.getScene().getWindow();
				AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Invaild Username OR Password");
				return;
			}

		}
	}

}