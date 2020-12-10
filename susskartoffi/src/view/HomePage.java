package view;

import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import dao.UserDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modell.Rezept;
import modell.User;
import modell.Zutat;
import modell.ZutatFX;
import modell.Zutat.Unit;

public class HomePage {
	private static Label logo ;
	private static UserDAO userDAO = new UserDAO();;
	private static BorderPane mainPane;
	private	static Button userProfil = null;
	private static User user;		
	private static ArrayList<Rezept> allRezeptList= null ;
	private static ListView<String> recepeList;


	public static VBox HomePage() {
		//		ArrayList of all the Recipes in DB
		ArrayList<Rezept> userRezepte =null;

		//		First menu Bar
		MenuBar menuBar = new MenuBar();
		// 		add Menu parts 
		Menu recipe = new Menu("Recipe");
		menuBar.getMenus().add(recipe);	



		//		<<<<this Block will create the recipe View part of the HomePage>>>>
		//	this block is build with BorderPane /(mainPane)
		//	at the Top stand a HomeBar
		//	at the left stand listview
		//	at the Center is HBox(receptBox), inside of if 
		mainPane = new BorderPane();

		//				Second Bar to the top of  BorderPane /(mainPane)
		HBox secondBar = secondBar();
		mainPane.setTop(secondBar);

		//		put to radio button to control recipe inside the list
		RadioButton rb1 = new RadioButton("Nur meine Rezepte");
		RadioButton rb2 = new RadioButton("All Rezepte");
		ToggleGroup tg = new ToggleGroup();
		rb1.setToggleGroup(tg);
		rb2.setToggleGroup(tg);
		HBox Radiobutton = new HBox(rb1,rb2);
		Radiobutton.setPadding(new Insets(10));



		//		the Left side of the BorderPane /(mainPane)
		//		create and fill ListView  with Recipes TitlegetUserRecipes

		//		call method to filter the user recipe and then filter the recipe name
	
		allRezeptList =  userDAO.getAllRezepte();
		ObservableList<String> iteams = FXCollections.observableArrayList(getList(allRezeptList));
		recepeList = new ListView<>(iteams);
//		if (!recepeList.getItems().isEmpty()) {
//			recepeList.getSelectionModel().select(0);
//		}
		





	



		//		add the viewList to the Left side of the BorderPane /(mainPane)
		Button create = new Button("Rezept erstellen");
		HBox hBox = new HBox(5);
		hBox.getChildren().add(create);
		hBox.setAlignment(Pos.BASELINE_CENTER);
		
		create.setOnAction(e -> {
			if(user!=null) {
			CreateRezeptDialon mc = new CreateRezeptDialon(user);
			Optional<String> container = mc.showAndWait();
			if(container.isPresent() && container.get() != null) {
				 recepeList.getItems().add(container.get());
			}
			
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Anmeldungsfehler ");
				alert.setHeaderText("Bitte Logen Sie sich an");
				alert.setContentText("Bitte versuchen Sie es noch einmal. ");


				Optional<ButtonType> error = alert.showAndWait();
			}
		});
		

		VBox vb = new VBox(5);
		vb.getChildren().addAll(hBox,Radiobutton,recepeList);
		vb.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
				+ "-fx-border-width: 2;" + "-fx-border-insets: 5;"
				+ "-fx-border-radius: 5;" + "-fx-border-color: black;");
		mainPane.setLeft(vb);









		recepeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				userDAO = new UserDAO();
				ArrayList<Rezept> rezeptList =  userDAO.getRezept(arg2);
				Rezept rezept = rezeptList.get(0);

				//


				//				this HBox(ReceptBox) stand at the center of BorderPane(mainPane)
				VBox receptBox= new VBox();
				receptBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
						+ "-fx-border-width: 2;" + "-fx-border-insets: 5;"
						+ "-fx-border-radius: 5;" + "-fx-border-color: blue;");


				//				Create/ edit / delete Recipes botton


				//				Pubic oder Private
						




				Button edit = new Button("Rezept bearbeiten");
				Button delete = new Button("Rezept löschen");
				delete.setOnAction(e -> {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Rezept Löschen");
					alert.setHeaderText("Drücken sie auf Ok für Bestätigen");
					Optional<ButtonType> result =alert.showAndWait();
					if(result.isPresent() && result.get() == ButtonType.OK) {
						userDAO.deleteRezept(rezept);
						recepeList.getItems().remove(recepeList.getSelectionModel().getSelectedIndex());
						
					
					}
					
					
				});
				
				edit.setOnAction(l-> {
					
					

				});
				
				
				
				HBox editBox = new HBox(edit,delete);
				editBox.setPadding(new Insets(10));
				editBox.setSpacing(12);
				//				this Block will create view for each recipe within the Center of the boderPane
				//				HBox(receptBox),icludes 
				//				I -GridPane for ( title, diät, lifstyle) 
				//				II-Hbox with the description
				//				III-Zuatten Liste Label
				//				IV- schritte

				//				I part of the ReceptBox 
				GridPane firstBox = partIrecipeBox(rezept);

				//				II part of the ReceptBox 
				GridPane SecondBox = new GridPane();

				Label beschreibunglbl= new Label("Beschreibung");
				beschreibunglbl.setStyle("-fx-text-fill:  #191110 ;");
				beschreibunglbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16 ));
				SecondBox.add(beschreibunglbl, 1, 0);
				firstBox.setMargin(beschreibunglbl, new Insets(10,10,10,10));

				Label beschreibung= new Label();
				beschreibung.setStyle("-fx-text-fill:  #191110 ;");
				beschreibung.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16 ));
				SecondBox.add(beschreibung, 1,1);
				firstBox.setMargin(beschreibung, new Insets(10,10,10,10));


				//				III part of the ReceptBox 
				//				Zuatten Liste Label
				Label zutatLbl = new Label("Zutaten Liste");
				zutatLbl.setStyle("-fx-text-fill:  #0c9ba7;");
				zutatLbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18 ));
				HBox zutatTitle = new HBox(zutatLbl);
				zutatTitle.setAlignment(Pos.CENTER);
				zutatTitle.setPadding(new Insets(10));
				
				VBox zutatBox = new VBox();
				ArrayList<Zutat> zutatList = rezept.getZutaten();
				for(Zutat zutat : zutatList) {
					
					Button bt = new Button(" - " + zutat.getName() +" "+ zutat.getmenge() +" "+ zutat.getUnit());
					bt.setCursor(Cursor.HAND);
					bt.setPadding(Insets.EMPTY);
					bt.setStyle("-fx-text-fill:  #010203 ;");
					bt.setFont(Font.font("Times New Roman", 14 ));
					bt.setStyle("-fx-text-fill:  #010203 ;-fx-background-color: transparent;");
					bt.setOnAction(e -> {		
						bt.setStyle("-fx-text-fill:  #010203 ;-fx-background-color: transparent; -fx-text-fill: #a7180c;");
				});
					
					HBox hb = new HBox(bt);
					hb.setPadding(new Insets(5));
					hb.setSpacing(10);

					zutatBox.getChildren().addAll(hb);

					bt.setOnAction(e -> {
						ZutatenDetail mc = new ZutatenDetail(zutat);
						Optional<ButtonType> container = mc.showAndWait();


						
					});
				}


				//				IV part of the ReceptBox 
				//					PUT Schritte LABEL 
				Label schrittLbl = new Label("Rezept Zubereitung");
				schrittLbl.setStyle("-fx-text-fill:  #0c9ba7;");
				schrittLbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18 ));
				HBox schritteTitle = new HBox(schrittLbl);
				schritteTitle.setAlignment(Pos.CENTER);
				schritteTitle.setPadding(new Insets(10));


				VBox schritteBox = new VBox();
				ArrayList<String> arSchritte = rezept.getSchritte();
				for(String schritt:arSchritte ) {
					schritteBox.getChildren().add(schritteGenerator(schritt, arSchritte.indexOf(schritt)));
				}

				receptBox.getChildren().addAll(firstBox,SecondBox,zutatTitle,zutatBox,schritteTitle,schritteBox,editBox);
				mainPane.setCenter(receptBox);
			}
		});

		VBox mainBox = new VBox(menuBar,mainPane);
		return mainBox;
	}



	private static List<String> getList(ArrayList<Rezept> arIn){
		List<String> arOut =arIn.stream().map(re -> re.getTitle()).collect(Collectors.toList());
		return arOut;

	}

	private static ArrayList<Rezept> getUserRecipes(ArrayList<Rezept> arIn, int userid){
		List<Rezept> temp =arIn.stream().filter( c -> c.getUserId() == userid).collect(Collectors.toList());
		ArrayList<Rezept> arOut = new ArrayList<Rezept>(temp);
		return arOut;

	}

	private static GridPane partIrecipeBox(Rezept rezept) {
		GridPane gridPane= new GridPane();


		//	create collomn and rows
		ColumnConstraints co1 = new ColumnConstraints();
		co1.setPercentWidth(120);
		ColumnConstraints co2 = new ColumnConstraints();
		co2.setPercentWidth(120);
		ColumnConstraints co3 = new ColumnConstraints();
		co3.setPercentWidth(120);
		gridPane.getColumnConstraints().addAll(co1,co2,co3);

		// 	 adding recipe Instance Variables 


		Label rezeptTitle = new Label();
		rezeptTitle.setText(rezept.getTitle());
		rezeptTitle.setStyle("-fx-text-fill:  #0c9ba7;");
		rezeptTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18 ));
		gridPane.setMargin(rezeptTitle, new Insets(10,10,10,10));
		
		gridPane.add(rezeptTitle, 1, 0);
		Label sichtbarkeit =new Label();
		if(rezept.istSichtbar())
			sichtbarkeit.setText("        Public");
		else sichtbarkeit.setText("        Private");
		sichtbarkeit.setStyle("-fx-text-fill:  #191110 ;");
		sichtbarkeit.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14 ));
//		mainPane.setMargin(sichtbarkeit, new Insets(10,10,10,10));
		gridPane.add(sichtbarkeit, 2,0);


		//	Rezept küche
		Label kuecheLbl =new Label("Küche");
		kuecheLbl.setStyle("-fx-text-fill:  #008000;");
		kuecheLbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18 ));
		gridPane.setMargin(kuecheLbl, new Insets(10,10,10,10));
		gridPane.add(kuecheLbl, 0, 1);

		Label kueche =new Label();
		kueche.setText(rezept.getHerkunft());
		kueche.setStyle("-fx-text-fill:  #191110 ;");
		kueche.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16 ));
		GridPane.setValignment(kueche, VPos.TOP);
		gridPane.setMargin(kueche, new Insets(10,10,10,10));
		gridPane.add(kueche, 0, 2);

		//	Rezept Diat
		Label Diatlbl =new Label("Diat");
		Diatlbl.setStyle("-fx-text-fill:  #008000;");
		Diatlbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18 ));
		gridPane.setMargin(Diatlbl, new Insets(10,10,10,10));
		gridPane.add(Diatlbl, 1, 1);

		Label diat =new Label();
		diat.setText(rezept.getDiaet());
		diat.setStyle("-fx-text-fill:  #191110 ;");
		diat.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16 ));
		GridPane.setValignment(diat, VPos.TOP);
		gridPane.setMargin(diat, new Insets(10,10,10,10));
		gridPane.add(diat, 1, 2);

		//	Rezept V : vegan oder vegetarisch
		Label lifstylLbl =new Label("Lifestil");
		lifstylLbl.setStyle("-fx-text-fill:  #008000;");
		lifstylLbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18 ));
		gridPane.setMargin(lifstylLbl, new Insets(10,10,10,10));
		gridPane.add(lifstylLbl, 2, 1);

		Label lifstyel =new Label();
		lifstyel.setText("VEGAN");
		lifstyel.setStyle("-fx-text-fill:  #191110 ;");
		lifstyel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16 ));
		GridPane.setValignment(lifstyel, VPos.TOP);
		gridPane.setMargin(lifstyel, new Insets(10,10,10,10));
		gridPane.add(lifstyel, 2, 2);

		return gridPane;




	}

	private static HBox secondBar() {

		//		create logo and align it in a bar
		logo = gitBild("C:\\Users\\anzah\\OneDrive\\Desktop\\Java code\\03-workPlace\\susskartoffi\\susskartoffi\\src\\Image\\appIcone.PNG");
		userProfil = new Button();
		userProfil.setVisible(false);

		//Add ChoiceBox and TextField to the second bar
		ChoiceBox<String> searchCB= new ChoiceBox();
		searchCB.getItems().addAll("Küche", "Lifestil", "Title");
		searchCB.setValue("Title");

		TextField textField = new TextField();
		textField.setPromptText("Search here!");
		textField.setOnKeyReleased(keyEvent ->
		{
			//            switch (choiceBox.getValue())//Switch on choiceBox value
			//            {
			//                case "First Name":
			//                    flPerson.setPredicate(p -> p.getFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
			//                    break;
			//                case "Last Name":
			//                    flPerson.setPredicate(p -> p.getLastName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
			//                    break;
			//                case "Email":
			//                    flPerson.setPredicate(p -> p.getEmail().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
			//                    break;
			//            }
		});

		searchCB.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
		{//reset table and textfield when new choice is selected
			if (newVal != null)
			{
				textField.setText("");
				//                flPerson.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
			}
		});
		HBox searchHB= new HBox(searchCB, textField);//Add choiceBox and textField to hBox
		searchHB.setAlignment(Pos.CENTER);//Center HBox



		//        create LogIn/Logout button
		Button log = new Button("");
		log.setStyle("-fx-background-color: transparent;-fx-text-fill:  #0c9ba7;");
		log.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13 ));
		log.setCursor(Cursor.HAND);
		log.setText("Anmelden");


		log.setOnAction(e -> {
			log.setStyle("-fx-background-color: transparent; -fx-text-fill: #a7180c;");

			user = LoginDialog.display();
			if(user!=null) {
				log.setText("Abmelden");
				logo.setVisible(false);			
				userProfil.setVisible(true);
				userProfil.setStyle("-fx-background-color: transparent;-fx-text-fill:  #0c9ba7;");
				userProfil.setFont(Font.font("Times New Roman", FontWeight.BOLD, 13 ));
				userProfil.setCursor(Cursor.HAND);
				userProfil.setText("Mein Profil");


			}


		});

		userProfil.setOnAction(e -> {
			MyProfileMD mc = new MyProfileMD(user);
			Optional<ButtonType> container = mc.showAndWait();

		});		



		//		add all the HomeBar parts in a HBox

		Region region1 = new Region();
		HBox.setHgrow(region1, Priority.ALWAYS);
		Region region2 = new Region();
		HBox.setHgrow(region2, Priority.ALWAYS);


		HBox homeBar = new HBox(userProfil,logo,region1,searchHB,region2, log);
		homeBar.setMaxHeight(50);
		homeBar.setPrefHeight(50);
		homeBar.setFillHeight(false);
		homeBar.setPadding(new Insets(10,10,10,10));
		homeBar.setAlignment(Pos.CENTER);//Center HBox
		homeBar.setSpacing(10);
		homeBar.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
				+ "-fx-border-width: 2;" + "-fx-border-insets: 5;"
				+ "-fx-border-radius: 5;" + "-fx-border-color: black;");
		return homeBar;

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





	private static Label gitBild(String bildUri) {
		Label logo = new Label();
		logo.setMinWidth(50);
		logo.setMinHeight(35);
		URI uri = Paths.get(bildUri).toUri();	
		ImageView im = new ImageView(uri.toString());
		logo.setGraphic(im);
		im.setPreserveRatio(true);
		im.setFitWidth(50);
		im.setFitHeight(35);
		logo.setPadding(new Insets(50));
		return logo;

	}

	
	
	
	
	
	
	
	
	
	
	
	
}

