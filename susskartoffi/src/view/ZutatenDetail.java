package view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modell.Zutat;

public class ZutatenDetail extends Dialog<ButtonType> {
	
	public  ZutatenDetail(Zutat zutat) {
		Label namelbl = new Label(" -Name : " + zutat.getName() );
		namelbl.setStyle("-fx-text-fill:  #010203 ;");
		namelbl.setFont(Font.font("Comic Sans MS", 12 ));

		Label labellbl = new Label(" -Label : " + zutat.getLabel());
		labellbl.setStyle("-fx-text-fill:  #010203 ;");
		labellbl.setFont(Font.font("Comic Sans MS", 12 ));
		
		Label mengelbl = new Label(" -menge : " + Integer.toString(zutat.getmenge()) );
		mengelbl.setStyle("-fx-text-fill:  #010203 ;");
		mengelbl.setFont(Font.font("Comic Sans MS", 12 ));


		Label unit = new Label(" -unit : " + zutat.getUnit());
		unit.setStyle("-fx-text-fill:  #010203 ;");
		unit.setFont(Font.font("Comic Sans MS", 12 ));

		Label laktosefreie = new Label();
		laktosefreie.setStyle("-fx-text-fill:  #010203 ;");
		laktosefreie.setFont(Font.font("Comic Sans MS", 12 ));
		if(zutat.isLaktosefreie())
			laktosefreie.setText(" -Laktosefreie");
		else laktosefreie.setText(" -nicht Laktosefreie");

		VBox box = new VBox(namelbl,labellbl ,mengelbl ,unit,laktosefreie);

		this.getDialogPane().setContent(box);
		ButtonType cancel = ButtonType.CANCEL;

		this.getDialogPane().getButtonTypes().addAll(cancel);

		this.setResizable(true);
		this.getDialogPane().setPrefSize(200, 150);

	}

	
	
	
}
