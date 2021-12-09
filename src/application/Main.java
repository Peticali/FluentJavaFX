package application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;

import com.peticali.scripts.Scripts;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Main extends Application{
	
	private TextField t;
	
	private void InsertIntoCalc(String ch){
		
		
		
	}
	
	private void CreateBttons(Scene scene) {
		
		
		FlowPane pane = (FlowPane) scene.lookup("#numbersPane");
		  
		ArrayList<String> buttonsToAdd = new ArrayList<String>();
		
		
		for (int i = 0; i <= 9; i++) {
			buttonsToAdd.add(String.valueOf(i));
		}
		
		buttonsToAdd.add("-");
		buttonsToAdd.add("+");
		buttonsToAdd.add("/");
		buttonsToAdd.add("*");
		buttonsToAdd.add(".");
		
		
		for (String i : buttonsToAdd) {
			Button button = new Button(i);
			button.setStyle("");
			button.setPrefSize(56, 60);
			
			button.setOnAction(new EventHandler<ActionEvent>(){
			     @Override
			     public void handle(ActionEvent event) {
			    	 
			         t.insertText(t.getText().length(), i);
			     }
			 });
			
			
			pane.getChildren().add(button);
		}
	
		
	}
	
	
  
	private double xOffset = 0;
	private double yOffset = 0;
	
	@Override
    public void start(Stage stage) {
		
		try{
			
			
			FXMLLoader loader1 = new FXMLLoader();
	        loader1.setLocation(getClass().getResource("Teste.fxml"));
	       
	        
	        AnchorPane rootLayout = loader1.load();
	        Scene scene = new Scene(rootLayout);
	       
	        stage.initStyle(StageStyle.UNDECORATED);
	        
	        
	        JMetro jMetro = new JMetro(Style.DARK);
	        jMetro.setScene(scene);
	        
	        
	        stage.setTitle("Calculator");
	        stage.setScene(scene);
	        stage.show();
	        
	        CreateBttons(scene);
	        stage.setAlwaysOnTop(true);
	        HWND hwnd = User32.INSTANCE.GetActiveWindow();
	        stage.setAlwaysOnTop(false);
	        
	       
	        BlurTest blurTest = new BlurTest();
			blurTest.BlurThis(hwnd);
			
		
			
			Scripts.SaveScene(scene);
			Rectangle DragRegion = (Rectangle) scene.lookup("#DragRegion");
			
			t = (TextField) scene.lookup("#calc"); 
			
			DragRegion.setOnMousePressed(new EventHandler<MouseEvent>() {
			     @Override
			     public void handle(MouseEvent event) {
			         xOffset = event.getSceneX();
			         yOffset = event.getSceneY();
			     }
			 });
	         
	         
			DragRegion.setOnMouseDragged(new EventHandler<MouseEvent>() {
	             @Override
	             public void handle(MouseEvent event) {
	                 stage.setX(event.getScreenX() - xOffset);
	                 stage.setY(event.getScreenY() - yOffset);
	             }
	         });
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
