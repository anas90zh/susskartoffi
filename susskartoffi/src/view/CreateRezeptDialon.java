package view;


import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

import dao.UserDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modell.Rezept;
import modell.User;
import modell.Zutat;

public class CreateRezeptDialon extends Dialog<ButtonType> {
	private ArrayList<String> schrittList = new  ArrayList<>();
	private ArrayList<Zutat> zutatList = new  ArrayList<>();
	private	String unittoggelGr;
	private String label;
	private boolean sichtbarkeit = true;
	private  int schrittcounter = 0;
	private Zutat zutat = null;
	public CreateRezeptDialon(User user) {



		BorderPane mainBox = new BorderPane();

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
		RadioButton rb1 = new RadioButton(" Public ");
		RadioButton rb2 = new RadioButton(" Private ");
		ToggleGroup tg = new ToggleGroup();
		rb1.setToggleGroup(tg);
		rb2.setToggleGroup(tg);
		HBox Radiobutton = new HBox(rb1,rb2);
		Radiobutton.setPadding(new Insets(10));	
		gp.add(Radiobutton, 1,3);
		rb2.setOnAction( e -> {
			sichtbarkeit = false;
		});


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
		Label RezLbl = new Label("Rezepte Zuweißung");
		RezLbl.setStyle("-fx-text-fill:  #0c9ba7;");
		RezLbl.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18 ));
		RezLbl.setPadding(new Insets(10));
		gp.add(RezLbl, 1, 6);

		Label schritte = new Label("Schritte : " );
		schritte.setPadding(new Insets(10));
		gp.add(schritte, 0, 7);

		TextField schritt = new TextField();
		Button add = new Button ("Add");

		VBox schritteBox = new VBox();
		add.setOnAction(e -> {
			schrittList.add(schritt.getText());

			Label schrittLbl = new Label("Rezept Zubereitung");
			schrittLbl.setStyle("-fx-text-fill:  #0c9ba7;");
			schrittLbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18 ));
			HBox schritteTitle = new HBox(schrittLbl);
			schritteTitle.setAlignment(Pos.CENTER);
			schritteTitle.setPadding(new Insets(10));

			if(!schritt.getText().isEmpty()) {
				schrittcounter++;
				schritteBox.getChildren().add(schritteGenerator(schritt.getText(), schrittcounter));

			}

			schritt.clear();

		});

		HBox hb1 = new HBox(schritteBox);
		add.setAlignment(Pos.BOTTOM_RIGHT);
		schritt.setAlignment (Pos.BOTTOM_LEFT);
		HBox hb2= new HBox(10);
		hb2.getChildren().addAll(schritt,add);
		hb2.setAlignment(Pos.CENTER_LEFT);
		VBox vb =new VBox(hb2,hb1);
		gp.add(vb, 1,7);


		//		Add Zutat
		Label zutatLbl = new Label("Zutaten Liste");
		zutatLbl.setStyle("-fx-text-fill:  #0c9ba7;");
		zutatLbl.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18 ));
		gp.add(zutatLbl, 1, 8);

		Label label2 = new Label("label : " );
		label2.setPadding(new Insets(10));
		gp.add(label2, 0, 9);

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
		gp.add(Radiobutton2, 1,9);


		Label name2 = new Label("name : " );
		name2.setPadding(new Insets(10));
		gp.add(name2, 0, 10);

		TextField nametx = new TextField();  	
		nametx.setMaxWidth(200);
		nametx.setPrefHeight(20);
		gp.add(nametx, 1, 10);

		Label menge = new Label("menge : " );
		menge.setPadding(new Insets(10));
		gp.add(menge, 0, 11);

		TextField mengetxf = new TextField();  	
		mengetxf.setMaxWidth(100);
		mengetxf.setPrefHeight(20);
		gp.add(mengetxf, 1, 11);


		Label laktosefreie = new Label("laktosefreie : " );
		laktosefreie.setPadding(new Insets(10));
		gp.add(laktosefreie, 0, 12);

		CheckBox ch= new CheckBox("Laktosefreie");
		gp.add(ch, 1,12);

		Label unit = new Label("Unit : " );
		unit.setPadding(new Insets(10));
		gp.add(unit, 0, 13);

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
		gp.add(Radiobutton3, 1,13);



		
		VBox zutatenBox = new VBox();
		Button add2 = new Button ("Add Zutat");
		add2.setOnAction(e -> {
			if(rb21.isSelected())
				rb21.setSelected(false);			
			if(rb22.isSelected())
				rb22.setSelected(false);	
			if(rb11.isSelected())
				rb11.setSelected(false);			
			if(rb12.isSelected())
				rb12.setSelected(false);
			if(rb13.isSelected())
				rb13.setSelected(false);
			
			
			if(!nametx.getText().isEmpty() && !mengetxf.getText().isEmpty() && !unittoggelGr.isEmpty() && !label.isEmpty()) {
				if(Pattern.compile("^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$").matcher(mengetxf.getText()).matches()){
					zutat =  new Zutat( nametx.getText(),unittoggelGr,Integer.parseInt(mengetxf.getText()),ch.isSelected(),label);
					zutatList.add(zutat);
					Button bt = new Button(" - " + zutat.getName() +" "+ zutat.getmenge() +" "+ zutat.getUnit());
					bt.setCursor(Cursor.HAND);
					bt.setPadding(Insets.EMPTY);
					bt.setStyle("-fx-text-fill:  #010203 ;");
					bt.setFont(Font.font("Times New Roman", 14 ));
					bt.setStyle("-fx-text-fill:  #010203 ;-fx-background-color: transparent;");
					bt.setOnAction(p -> {
						if(zutat!=null) {
							ZutatenDetail mc = new ZutatenDetail(zutat);
							Optional<ButtonType> container = mc.showAndWait();
	
						}
						

						bt.setStyle("-fx-text-fill:  #010203 ;-fx-background-color: transparent; -fx-text-fill: #a7180c;");
				});
					Button delete = new Button("löschen");
					delete.setPadding(Insets.EMPTY);

					delete.setPrefHeight(10);
					HBox hb = new HBox(bt,delete);
					hb.setPadding(new Insets(5));
					hb.setSpacing(10);
					zutatenBox.getChildren().add(hb);
					delete.setOnAction(p -> {
						if((zutatList.size() - 1) <0)
						zutatList.remove(zutatList.size() - 1); 
						zutatenBox.getChildren().remove(hb);
					});
				}
				
				
				nametx.clear();
				mengetxf.clear();
				ch.setSelected(false);
				tg.selectToggle(null);
			

				
			}

			
	

		});	

		add2.setAlignment(Pos.BOTTOM_RIGHT);
		gp.add(add2, 1,14);
		gp.add(zutatenBox, 1,15);



		//		-------------------

		Label VorbereitungDauer = new Label("Vorbereitung Dauer : " );
		gp.add(VorbereitungDauer, 0, 16);
		VorbereitungDauer.setPadding(new Insets(10));

		TextField VorbereitungDauertf = new TextField();
		VorbereitungDauertf.setMaxWidth(100);
		VorbereitungDauertf.setPrefHeight(20);
		gp.add(VorbereitungDauertf, 1, 16);

		//	--------------------------------
		Button bn = new Button("Rezept erstellen");
		bn.setOnAction(e -> {
	
			if(!kuchetxf.getText().isEmpty() && !diaettxf.getText().isEmpty() && !VorbereitungDauertf.getText().isEmpty() && !titletxf.getText().isEmpty() && !kostentxf.getText().isEmpty()) {
				if(Pattern.compile("^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$").matcher(VorbereitungDauertf.getText()).matches() && Pattern.compile("^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$").matcher(kostentxf.getText()).matches()) {
					UserDAO dao= new UserDAO();
					Rezept rezept = new Rezept(user.getUserId(), kuchetxf.getText(), diaettxf.getText(), Integer.parseInt(VorbereitungDauertf.getText()), titletxf.getText(), sichtbarkeit,
							" bescreibung", Integer.parseInt(nr.getText()), Integer.parseInt(kostentxf.getText()), zutatList, schrittList);

					dao.CreateRezept(rezept, user.getUserId());

				}

			}

		});
		mainBox.setBottom(bn);

		mainBox.setCenter(gp);


		this.getDialogPane().setContent(mainBox);
		ButtonType cancel = ButtonType.CANCEL;
		ButtonType ok = ButtonType.APPLY;

		this.getDialogPane().getButtonTypes().addAll(ok,cancel);

		this.setResizable(true);
		this.getDialogPane().setPrefSize(540, 380);



	}

	private static HBox schritteGenerator(String st, int n) {
		Label schrittlbl = new Label(" - Schtitte " + n +" : ");
		Label schritt = new Label(st);
		schrittlbl.setStyle("-fx-text-fill:  #010203 ;");
		schrittlbl.setFont(Font.font("Times New Roman", 14 ));
		schritt.setStyle("-fx-text-fill:  #010203 ;");
		schritt.setFont(Font.font("Times New Roman", 14 ));
		HBox hb =new HBox(schrittlbl, schritt);
		hb.setPadding(new Insets(5));
		return hb;

	}


}
