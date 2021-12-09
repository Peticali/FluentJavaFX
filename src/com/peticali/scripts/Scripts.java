package com.peticali.scripts;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javafx.event.ActionEvent;  
import javafx.fxml.FXML;
import application.Main;



public class Scripts {
	
	private ScriptEngineManager manager = new ScriptEngineManager();
	private ScriptEngine engine = manager.getEngineByName("js");
	
	@FXML
    private void Calculate(ActionEvent event) {
        event.consume();
       
			try {
				String result = String.valueOf(engine.eval(Main.t.getText()));
				
				if (result != "null") {
					Main.t.setText(result);
				}
				
			} catch (ScriptException e) {
				e.printStackTrace();
			}
    }

	@FXML
    private void DelALL(ActionEvent event) {
		Main.t.clear();
	}
	
}


