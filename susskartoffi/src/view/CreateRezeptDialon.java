package view;


import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateRezeptDialon extends Dialog<ButtonType> {
	
	public CreateRezeptDialon() {
		
		GridPane gp = new GridPane();
		Label header = new Label("Rezept Erstellen" );
		header.setStyle("-fx-text-fill:  #0c9ba7;");
		header.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18 ));
		header.setPadding(new Insets(20));
		gp.add(header, 3, 0);

		
		Label title = new Label("Title : " );
		gp.add(title, 0, 1);
		title.setPadding(new Insets(10));

		TextField titletxf = new TextField();  
		titletxf.setPadding(new Insets(10));
		gp.add(titletxf, 1, 1);

		Label diaet = new Label("Diät : " );
		diaet.setPadding(new Insets(10));
		gp.add(diaet, 0, 2);

		TextField diaettxf = new TextField();  	
		diaettxf.setPadding(new Insets(10));
		gp.add(diaettxf, 1, 2);
		
		Label kuche = new Label("Küche : " );
		kuche.setPadding(new Insets(10));
		gp.add(kuche, 0, 3);

		TextField kuchetxf = new TextField();  
		kuchetxf.setPadding(new Insets(10));
		gp.add(kuchetxf, 1, 3);

		Label sichtbarkeit = new Label("Privacy : " );
		sichtbarkeit.setPadding(new Insets(10));
		gp.add(sichtbarkeit, 0, 4);

		Label portionen = new Label("Portionen : " );
		portionen.setPadding(new Insets(10));
		gp.add(portionen, 0, 5);

		Label kosten = new Label("kosten : " );
		kosten.setPadding(new Insets(10));
		gp.add(kosten, 0, 6);

		TextField kostentxf = new TextField();  
		kostentxf.setPadding(new Insets(10));
		gp.add(kostentxf, 1, 6);

		
		
		
		
		this.getDialogPane().setContent(gp);
		ButtonType cancel = ButtonType.CANCEL;

		this.getDialogPane().getButtonTypes().addAll(cancel);

		this.setResizable(true);
		this.getDialogPane().setPrefSize(540, 380);
		
		
		
	}
	

	
	
	
	
	
	
	
	
	
	
}
