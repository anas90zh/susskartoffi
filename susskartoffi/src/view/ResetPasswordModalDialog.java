package view;


import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import modell.User;

public class ResetPasswordModalDialog extends Dialog<ButtonType>{

	public ResetPasswordModalDialog(User user) {
		
		
		this.setTitle("Passwort zurücksetzen ");
		this.setHeaderText("Passwort zurücksetzen");


		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(40, 40, 40, 40));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		// Add Name Block
		Label nameLabel = new Label("Full Name : " + user.getName());
		gridPane.add(nameLabel, 0,1);

		// Add UserName Block
		Label userNameLabel = new Label("User Name : " + user.getUserName());
		gridPane.add(userNameLabel, 0,2);
		
		// Add Lifstyle Block
		Label lifestyleLabel = new Label("lifestyleLabel " + user.getLifstyle());
		
		// Add Password Block
		Label passwordLabel = new Label("Password : ");
		gridPane.add(passwordLabel, 0, 5);	
		PasswordField passwordField = new PasswordField();
		passwordField.setMaxWidth(200);
		passwordField.setPrefHeight(40);
		gridPane.add(passwordField, 1, 5);

		// Add Submit Button
		
		Button submitButton = new Button("Submit");
		submitButton.setPrefHeight(40);
		submitButton.setPrefWidth(100);
		
		gridPane.add(submitButton, 0, 6, 2, 1);
		GridPane.setHalignment(submitButton, HPos.CENTER);
		GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

		
		submitButton.setOnAction((ActionEvent e) -> {
			if(!passwordField.getText().isEmpty()){
				UserDAO userDAO = new UserDAO();
			userDAO.updatePassword(user.getUserId(), passwordField.getText());

			}
			

		});


		this.getDialogPane().setContent(gridPane);
		ButtonType cancel = ButtonType.CANCEL;

		this.getDialogPane().getButtonTypes().addAll(cancel);

		this.setResizable(true);
		this.getDialogPane().setPrefSize(540, 380);

		
		
	}

}
