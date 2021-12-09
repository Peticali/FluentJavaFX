package com.peticali.scripts;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.event.ActionEvent;  
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;




public class Scripts {
	
	
	private static Scene scene; 
	private static TextField t; 
	
	private ScriptEngineManager manager = new ScriptEngineManager();
	private ScriptEngine engine = manager.getEngineByName("js");
	
	public static void SaveScene(Scene s) {
		
		scene = s;
		t = (TextField) scene.lookup("#calc");   
		
	}
	
	@FXML
    private void Calculate(ActionEvent event) {
        event.consume();
       
			try {
				String result = String.valueOf(engine.eval(t.getText()));
				
				if (result != "null") {
					t.setText(result);
				}
				
			} catch (ScriptException e) {
				e.printStackTrace();
			}
    }

	@FXML
    private void DelALL(ActionEvent event) {
		t.clear();
	}
	
}


