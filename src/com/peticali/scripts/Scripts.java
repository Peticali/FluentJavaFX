package com.peticali.scripts;

import javafx.event.ActionEvent;  
import javafx.fxml.FXML;




public class Scripts {
	
	
	
	//@FXML
    //private Label lbl ;

	
	@FXML
    private void printHelloWorld(ActionEvent event) {
        event.consume();
        
        System.out.println("Hello, World!");
        //lbl.setText("opa");
    }

	
	
}


