package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modell.Rezept;
import modell.User;

public class MyProfileMD  extends Dialog<ButtonType>{

	public MyProfileMD(User user) {

		this.setTitle("Mein Profil");
		this.setHeaderText("Mein Profil");
		BorderPane porderPane = new BorderPane();

	


		Label lbl = new Label("Hallo " + user.getName());
		
		Label logo = new Label();
		logo.setMinWidth(60);
		logo.setMinHeight(60);
		URI uri = Paths.get("C:\\Users\\anzah\\OneDrive\\Desktop\\Java code\\03-workPlace\\susskartoffi\\susskartoffi\\src\\Image\\cooking.PNG").toUri();	
		ImageView im = new ImageView(uri.toString());
		logo.setGraphic(im);
		im.setPreserveRatio(true);
		im.setFitWidth(50);
		im.setFitHeight(35);
		logo.setPadding(new Insets(0));
		logo.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
		        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
		        + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
		Button btn = new Button("Bild ändern");

		VBox hb = new VBox(lbl,logo,btn);


		porderPane.setRight(hb);
		porderPane.setAlignment(hb, Pos.TOP_RIGHT);

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(40, 40, 40, 40));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
//		gridPane.setGridLinesVisible(true);

		// Add Name Block
		Label nameLabel = new Label("Name : " + user.getName());
		gridPane.add(nameLabel, 0,1);

		// Add UserName Block
		Label userNameLabel = new Label("Username : " + user.getUserName());
		gridPane.add(userNameLabel, 0,2);

		// Add Lifstyle Block
		Label lifestyleLabel = new Label("lifestyleLabel " + user.getLifstyle());

		// Add Password Block
		Label passwordLabel = new Label("Passwort : ");
		gridPane.add(passwordLabel, 0, 5);	
		Label passwordField = new Label();
		passwordField.setText(user.getPassword());
		gridPane.add(passwordField, 1, 5);

		// Add Submit Button

		Button PasswordAenderen = new Button("Passwort änderen");
		PasswordAenderen.setStyle("-fx-background-color: transparent;");
		PasswordAenderen.setOnAction(e -> {		
			PasswordAenderen.setStyle("-fx-background-color: transparent; -fx-text-fill: #a7180c;");
				
				ResetPasswordModalDialog  md = new ResetPasswordModalDialog( user );
				Optional<ButtonType> container = md.showAndWait();
				
				
				
		});
		PasswordAenderen.setCursor(Cursor.HAND);		
		gridPane.add(PasswordAenderen, 0, 6);


			
			



		porderPane.setCenter(gridPane);

		this.getDialogPane().setContent(porderPane);
		ButtonType cancel = ButtonType.CANCEL;

		this.getDialogPane().getButtonTypes().addAll(cancel);

		this.setResizable(true);
		this.getDialogPane().setPrefSize(540, 380);



	}

}


