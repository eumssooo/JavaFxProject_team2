package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SeatMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("movie.fxml"));
		Parent root = loader.load();
		
		SeatController ctrl = loader.getController();
		ctrl.setRoot(root);
		System.out.println("test");
	
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("자리 확인");
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
