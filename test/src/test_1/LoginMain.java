package test_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import test_1.Controller.LoginController;

public class LoginMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../login.fxml"));
		
		Parent root = loader.load();
		
		LoginController ctrl = loader.getController();
		ctrl.setRoot(root);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("로그인");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
