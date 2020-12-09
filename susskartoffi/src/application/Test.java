package application;

import java.util.regex.Pattern;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class Test {

	public static void main(String[] args) {
		
		String st = "1s";
		if(Pattern.compile("^[1-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$").matcher(st).matches()) {
			System.out.println("tue");
		}
	}

}
