package application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.HomePage;


public class FXMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		
		VBox startpage = HomePage.HomePage();
		
		
		primaryStage.setScene(new Scene(startpage,750,950));
		primaryStage.setTitle("title");
		primaryStage.show();
	}

	

	public static void main(String[] args) {
		launch(args);
	}




}
