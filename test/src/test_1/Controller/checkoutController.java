package test_1.Controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class checkoutController {
	@FXML
	private Button logoutButton;
	
//	@FXML
//	private AnchorPane scenePane;
	Stage stage;
	
	Parent checkout;

	public void setCheckout(Parent checkout) {
		// TODO Auto-generated method stub
		this.checkout = checkout;
	}

	public void logout(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("로그아웃 하시겠습니까?");
		alert.setContentText("상영시간 이후 환불은 어려울 수 있습니다.");

		if(alert.showAndWait().get()==ButtonType.OK) {
			stage = (Stage) checkout.getScene().getWindow();
			System.out.println("로그아웃되셨습니다.");
			stage.close();
		}
//		stage = (Stage) checkout.getScene().getWindow();

	}

	public void add(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("More Movie");
		alert.setHeaderText("추가 예매 하시겠습니까?");
		alert.setContentText("페이지로 이동합니다.");

		if(alert.showAndWait().get()==ButtonType.OK) { // 날짜 선택 페이지로 이동//
			stage = (Stage) checkout.getScene().getWindow();
			
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../SelectMovie_1_Movie.fxml"));
			Parent selectDate = null;
			
			try {
				selectDate = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SelectMovieController ctrl = loader.getController();
			ctrl.setSelectDate(selectDate);
			
			stage.setScene(new Scene(selectDate));
			stage.setTitle("상영 날짜 선택");
			stage.show();
		}
//		stage = (Stage) checkout.getScene().getWindow();

	}

	public void ticketcheck(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Ticket Check?");
		alert.setHeaderText("예약한 티켓을 확인하시겠습니까?");
		alert.setContentText("상영시간 이후 환불은 어려울 수 있습니다.");

		if(alert.showAndWait().get()==ButtonType.OK) {// 다음 페이지 이동
			stage = (Stage) checkout.getScene().getWindow();
			
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../TicketConfirm.fxml"));
			Parent root = null;
			
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ConfirmController ctrl = loader.getController();
			ctrl.setRoot(root);
			
			stage.setScene(new Scene(root));
			stage.setTitle("티켓 확인");
			stage.show();
		}
//		stage = (Stage) checkout.getScene().getWindow();

	}

}
