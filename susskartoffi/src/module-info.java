module sueﬂkartoffi {
	requires javafx.graphics;
	requires java.sql;
	requires javafx.controls;
	requires java.xml;
	requires java.desktop;
	requires javafx.base;
	exports application;
    opens modell to javafx.base;
    }