package application;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class Test {

	public static void main(String[] args) {
		
		RadioButton rb21 = new RadioButton(" g  ");
		RadioButton rb22= new RadioButton(" ml ");
		ToggleGroup tg2 = new ToggleGroup();
		rb21.setToggleGroup(tg2);
		rb22.setToggleGroup(tg2);
		ToggleButton units = (ToggleButton) tg2.getSelectedToggle();

	System.out.println(units == null);
	
	}

}
