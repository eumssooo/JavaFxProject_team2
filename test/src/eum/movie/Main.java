package eum.movie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../SelectMovie_1_Date.fxml")); //경로 수정
		
		Parent selectDate = loader.load();

		SelectMovieController ctrl = loader.getController();
		ctrl.setSelectDate(selectDate);
				
		primaryStage.setScene(new Scene(selectDate));
		primaryStage.setTitle("상영 날짜 선택");
		primaryStage.show();
		
		// 상영날짜 선택 콤보박스 내용 입력 (로그인 이후에 넣기)
		ComboBox<String> cmbDate = (ComboBox<String>) selectDate.lookup("#cmbDate");
		cmbDate.getItems().addAll("2월 15일","2월 16일","2월 17일","2월 18일","2월 19일");
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
