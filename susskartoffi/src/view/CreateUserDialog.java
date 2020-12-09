package view;



import dao.UserDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import modell.User;

public class CreateUserDialog extends Dialog<ButtonType>{
	public String lifestyle = null;

	public CreateUserDialog() {
		this.setTitle("Registration");
		this.setHeaderText("Registration");


		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(40, 40, 40, 40));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		// Add Name Block
		Label nameLabel = new Label("Full Name : ");
		gridPane.add(nameLabel, 0,1);
		TextField nameField = new TextField();
		nameField.setMaxWidth(200);
		nameField.setPrefHeight(40);
		gridPane.add(nameField, 1,1);

		// Add UserName Block
		Label userNameLabel = new Label("UserName : ");
		gridPane.add(userNameLabel, 0,2);
		TextField userNameField = new TextField();
		userNameField.setMaxWidth(200);
		nameField.setPrefHeight(40);
		gridPane.add(userNameField, 1,2);
		// Add Lifstyle Block
		Label lifestyleLabel = new Label("lifestyleLabel ");
		gridPane.add(lifestyleLabel, 0,3);
		HBox hblifstyle = new HBox();
		hblifstyle.setPadding(new Insets(10));
		hblifstyle.setSpacing(10);
		RadioButton rb1 = new RadioButton("VEGAN");
		rb1.setUserData("VEGAN");
		RadioButton rb2 = new RadioButton("VEGETARIA");
		rb2.setUserData("VEGAN");
		RadioButton rb3 = new RadioButton("MEATLOVER");
		rb3.setUserData("VEGAN");
		RadioButton rb4 = new RadioButton("ICHESSEALLES");
		rb4.setUserData("VEGAN");

		ToggleGroup lifstyleTG = new ToggleGroup();

		rb1.setToggleGroup(lifstyleTG);		
		rb2.setToggleGroup(lifstyleTG);		
		rb3.setToggleGroup(lifstyleTG);		
		rb4.setToggleGroup(lifstyleTG);		

		hblifstyle.getChildren().addAll(rb1, rb2, rb3, rb4);
		gridPane.add(hblifstyle, 1,3);


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

		lifstyleTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				if(arg2 !=null) {
					lifestyle = lifstyleTG.getSelectedToggle().getUserData().toString();

				}

			}

		});
		submitButton.setOnAction(e -> {
			if(!nameField.getText().isEmpty() && !userNameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
				User user = new User(userNameField.getText(),nameField.getText(), passwordField.getText(), lifestyle);
				UserDAO userDAO = new UserDAO();
				userDAO.createUser(user);
				submitButton.setDisable(true);
			}
			


		});


		this.getDialogPane().setContent(gridPane);
		ButtonType cancel = ButtonType.CANCEL;
	

		this.getDialogPane().getButtonTypes().addAll(cancel);

		this.setResizable(true);
		this.getDialogPane().setPrefSize(600, 380);



	}


}
