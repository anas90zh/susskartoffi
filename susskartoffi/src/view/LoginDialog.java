package view;

import java.util.ArrayList;
import java.util.Optional;

import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modell.User;

public class LoginDialog {
	private static User user=null;

	public static User display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Admelden");
		window.setWidth(400);
		window.setHeight(250);
		window.initStyle(StageStyle.UTILITY);

		// Set up the JavaFX button controls and listeners and the text fields
		// for the login info. The button listeners set the login values





		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);

		//User name input 
		Label lbUsername = new Label("User Name");
		lbUsername.setTextAlignment(TextAlignment.CENTER);
		TextField txUsername = new TextField();
		grid.add(lbUsername, 0, 0);
		grid.add(txUsername, 1, 0);

		//password input
		Label lbPassword = new Label("Password");
		lbPassword.setTextAlignment(TextAlignment.CENTER);
		PasswordField txPassword = new PasswordField();
		grid.add(lbPassword, 0, 1);
		grid.add(txPassword, 1, 1);

		//password vergessen btn
		Button passVergessenBtn = new Button("Passwort vergessen?");
		passVergessenBtn.setStyle("-fx-background-color: transparent;");
		passVergessenBtn.setOnAction(e -> {		
				passVergessenBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a7180c;");
		});
		passVergessenBtn.setCursor(Cursor.HAND);
		HBox hbpassBtn = new HBox();
		hbpassBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbpassBtn.getChildren().add(passVergessenBtn);
		grid.add(hbpassBtn, 0, 2);

		passVergessenBtn.setOnAction((ActionEvent e) -> {

						if(!txUsername.getText().isBlank()) {
							UserDAO userDao = new UserDAO();

							ArrayList<User> arUser = userDao.getUser(txUsername.getText());
							
							ResetPasswordModalDialog  md = new ResetPasswordModalDialog( arUser.get(0) );
							Optional<ButtonType> container = md.showAndWait();
						}
					

		});

		//log in BTN
		Button anmelden = new Button("Anmelden");
		HBox hbanmelden = new HBox();
		hbanmelden.setAlignment(Pos.BOTTOM_RIGHT);
		hbanmelden.getChildren().add(anmelden);
		grid.add(hbanmelden, 1, 2);

		anmelden.setOnAction((ActionEvent e) -> {
			User tempUser = null;
			ArrayList<User> userList = null;

			if(!txUsername.getText().isEmpty() & !txPassword.getText().isEmpty()) {
				UserDAO userDAO = new UserDAO();
				userList = new ArrayList<>();

				userList = userDAO.getUser(txUsername.getText());

				if(!userList.isEmpty()) {
					if(userList.size()>0)
						tempUser = userList.get(0);		
					if (!tempUser.getPassword().equals(txPassword.getText())) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Anmeldungsfehler ");
						alert.setHeaderText("Eingegebener Nutzername oder Passwort ist falsch");
						alert.setContentText("Bitte versuchen Sie es noch einmal. ");


						Optional<ButtonType> error = alert.showAndWait();
					}else {
						user = tempUser;
					}


					
					
				}else {
					Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Anmeldungsfehler ");
				alert.setHeaderText("Der angegebene Benutzer ist dem System nicht bekannt");
				alert.setContentText("Benutzer existiert nicht");
				Optional<ButtonType> error = alert.showAndWait();

				}


			}
		
	




		window.close();

	});



		//create new User Block
		Button newUserBtn = new Button("Neues Konto erstellen");
		newUserBtn.setOnAction((ActionEvent e) -> {

			NeueUserModalDialog md = new NeueUserModalDialog();

			Optional<ButtonType> container = md.showAndWait();
		});

		grid.add(newUserBtn, 0,5 );






		//
		window.setScene(new Scene(grid, 700, 400));
		window.showAndWait();
		return user;
}

}
