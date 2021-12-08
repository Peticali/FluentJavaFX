package application;
	
import java.io.IOException;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;


public class Main extends Application{
	
	 @Override
    public void start(Stage stage) {
		
		try{
			
		
			FXMLLoader loader1 = new FXMLLoader();
	        loader1.setLocation(getClass().getResource("Teste.fxml"));
	        AnchorPane rootLayout = loader1.load();
	        Scene scene = new Scene(rootLayout);
	        
	        JMetro jMetro = new JMetro(Style.DARK);
	        jMetro.setScene(scene);
	        
	        
	        stage.setTitle("Calculator");
	        stage.setScene(scene);
	        stage.show();
	        stage.setAlwaysOnTop(true);
	        HWND hwnd = User32.INSTANCE.GetActiveWindow();
	        stage.setAlwaysOnTop(false);
	        
	        System.out.print(hwnd);
	        BlurTest blurTest = new BlurTest();
			blurTest.BlurThis(hwnd);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
