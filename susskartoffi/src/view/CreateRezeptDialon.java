package view;


import java.util.ArrayList;

import application.FXMain;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modell.Zutat;
import modell.Zutat.Unit;

public class CreateRezeptDialon extends Dialog<ButtonType> {
	private ArrayList<String> schrittList = new  ArrayList<>();
	private ArrayList<Zutat> zutatList = new  ArrayList<>();
	private	String unittoggelGr;
	private String label;
	
	public CreateRezeptDialon() {



		BorderPane mainBox =new BorderPane();

		Label header = new Label("Rezept Erstellen" );
		header.setStyle("-fx-text-fill:  #0c9ba7;");
		header.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 18 ));
		header.setPadding(new Insets(10));
		mainBox.setTop(header);
		mainBox.setAlignment(header, Pos.TOP_CENTER);
		//		grid Pane füe all the in put
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(20, 20, 20, 20));
		gp.setHgap(10);
		gp.setVgap(10);



		Label title = new Label("Title : " );
		gp.add(title, 0, 0);
		title.setPadding(new Insets(10));

		TextField titletxf = new TextField();
		titletxf.setMaxWidth(200);
		titletxf.setPrefHeight(20);
		gp.add(titletxf, 1, 0);

		Label diaet = new Label("Diät : " );
		diaet.setPadding(new Insets(10));
		gp.add(diaet, 0, 1);

		TextField diaettxf = new TextField();  	
		diaettxf.setMaxWidth(200);
		diaettxf.setPrefHeight(20);
		gp.add(diaettxf, 1, 1);



		Label kuche = new Label("Küche : " );
		kuche.setPadding(new Insets(10));
		gp.add(kuche, 0, 2);

		TextField kuchetxf = new TextField();  
		kuchetxf.setMaxWidth(200);
		kuchetxf.setPrefHeight(20);
		gp.add(kuchetxf, 1, 2);

		Label sichtbarkeitlb = new Label("Privacy : " );
		sichtbarkeitlb.setPadding(new Insets(10));
		gp.add(sichtbarkeitlb, 0, 3);
		RadioButton rb1 = new RadioButton("Public");
		RadioButton rb2 = new RadioButton("Private");
		ToggleGroup tg = new ToggleGroup();
		rb1.setToggleGroup(tg);
		rb2.setToggleGroup(tg);
		HBox Radiobutton = new HBox(rb1,rb2);
		Radiobutton.setPadding(new Insets(10));	
		gp.add(Radiobutton, 1,3);


		Label portionen = new Label("Portionen : " );
		portionen.setPadding(new Insets(10));
		gp.add(portionen, 0, 4);

		Label nr = new Label ("4");
		Label warning = new Label ("vier ist die niedrigste, die es geben kann ");
		warning.setVisible(false);
		Button plus = new Button ("+");
		plus.setOnAction(e -> {
			int n = Integer.parseInt(nr.getText());
			n++;
			nr.setText(Integer.toString(n));
			if(warning.isVisible())
			warning.setVisible(false);

		});
		Button minus = new Button (" -");
		minus.setOnAction(e -> {
			int n = Integer.parseInt(nr.getText());
			if(n>4)
				n--;
			else     warning.setVisible(true);
				
				
			nr.setText(Integer.toString(n));
		});
		plus.setAlignment(Pos.BOTTOM_RIGHT);
		minus.setAlignment (Pos.BOTTOM_LEFT);
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(plus,nr,minus,warning);
		hBox.setAlignment(Pos.CENTER);
		gp.add(hBox, 1, 4);

		
		
		


		Label kosten = new Label("kosten : " );
		kosten.setPadding(new Insets(10));
		gp.add(kosten, 0, 5);

		TextField kostentxf = new TextField();  
		kostentxf.setMaxWidth(100);
		kostentxf.setPrefHeight(20);
		gp.add(kostentxf, 1, 5);


//		Add Schritte
		Label schritte = new Label("Schritte : " );
		schritte.setPadding(new Insets(10));
		gp.add(schritte, 0, 6);
		
		TextField schritt = new TextField();
		Button add = new Button ("Add");
		add.setOnAction(e -> {
			schrittList.add(schritt.getText());
			schritt.clear();
		});
		
		add.setAlignment(Pos.BOTTOM_RIGHT);
		schritt.setAlignment (Pos.BOTTOM_LEFT);
		HBox hb2= new HBox(10);
		hb2.getChildren().addAll(schritt,add);
		hb2.setAlignment(Pos.CENTER_LEFT);
		gp.add(hb2, 1,6);
		
		
//		Add Zutat
		Label label2 = new Label("label : " );
		label2.setPadding(new Insets(10));
		gp.add(label2, 0, 7);

		RadioButton rb11 = new RadioButton("VEGAN  ");
		rb11.setUserData("VEGAN");
		RadioButton rb12 = new RadioButton("VEGETARISCH  ");
		rb12.setUserData("VEGETARISCH");
		RadioButton rb13 = new RadioButton("PLANTBASED  ");
		rb13.setUserData("PLANTBASED");


		ToggleGroup tg1 = new ToggleGroup();
		
		tg1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				if(arg2 !=null) {
					label = tg1.getSelectedToggle().getUserData().toString();
					
				}
					
			}
			
		});
		rb11.setToggleGroup(tg1);
		rb12.setToggleGroup(tg1);
		rb13.setToggleGroup(tg1);
		
		HBox Radiobutton2 = new HBox(rb11,rb12,rb13);

		Radiobutton2.setAlignment(Pos.BASELINE_LEFT);
		Radiobutton2.setPadding(new Insets(10));	
		gp.add(Radiobutton2, 1,7);

//		............
		
		
		Label name2 = new Label("name : " );
		name2.setPadding(new Insets(10));
		gp.add(name2, 0, 8);

		TextField nametx = new TextField();  	
		nametx.setMaxWidth(200);
		nametx.setPrefHeight(20);
		gp.add(nametx, 1, 8);
		
		Label kalorien = new Label("kalorien : " );
		kalorien.setPadding(new Insets(10));
		gp.add(kalorien, 0, 9);
	
		TextField kalorientxf = new TextField();  	
		kalorientxf.setMaxWidth(100);
		kalorientxf.setPrefHeight(20);
		gp.add(kalorientxf, 1, 9);
		
		
		Label laktosefreie = new Label("laktosefreie : " );
		laktosefreie.setPadding(new Insets(10));
		gp.add(laktosefreie, 0, 10);
		
		CheckBox ch= new CheckBox("Laktosefreie");
		gp.add(ch, 1,10);
		
		Label unit = new Label("Unit : " );
		unit.setPadding(new Insets(10));
		gp.add(unit, 0, 11);
		
		RadioButton rb21 = new RadioButton(" g  ");
		rb21.setUserData("g");
		RadioButton rb22= new RadioButton(" ml ");
		rb22.setUserData("ml");
		ToggleGroup tg2 = new ToggleGroup();
		rb21.setToggleGroup(tg2);
		rb22.setToggleGroup(tg2);
		tg2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				if(arg2 !=null) {
					unittoggelGr = tg2.getSelectedToggle().getUserData().toString();
					
				}
					
			}
			
		});
		
		
		HBox Radiobutton3 = new HBox(rb21,rb22);
		Radiobutton3.setPadding(new Insets(5));	
		Radiobutton3.setAlignment(Pos.CENTER_LEFT);
		gp.add(Radiobutton3, 1,11);
		
		
		
		Label add1= new Label("Add Zutaten: " );
		name2.setPadding(new Insets(10));
		gp.add(add1, 0, 12);
		
		Button add2 = new Button ("Add");
		add2.setOnAction(e -> {

		if(!nametx.getText().isEmpty() && !kalorientxf.getText().isEmpty() && !unittoggelGr.isEmpty() && !label.isEmpty()) {
			Zutat zutat =  new Zutat(label, unittoggelGr, FXMain.getRezeptId(), nametx.getText(), Integer.parseInt(kalorientxf.getText()), ch.isSelected());
			zutatList.add(zutat);
		}
		
		});	
		gp.add(add2, 1,12);

		add2.setAlignment(Pos.BOTTOM_RIGHT);
		
		
//	--------------------------------


	
		
		
		mainBox.setCenter(gp);


		this.getDialogPane().setContent(mainBox);
		ButtonType cancel = ButtonType.CANCEL;

		this.getDialogPane().getButtonTypes().addAll(cancel);

		this.setResizable(true);
		this.getDialogPane().setPrefSize(540, 380);



	}












}
