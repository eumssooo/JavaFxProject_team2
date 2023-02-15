package test_1.Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
		stage = (Stage) checkout.getScene().getWindow();

		System.out.println("로그아웃 하셨습니다.");
	}

	public void add(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("More Movie");
		alert.setHeaderText("추가 예매 하시겠습니까?");
		alert.setContentText("페이지로 이동합니다.");

		if(alert.showAndWait().get()==ButtonType.OK) { //페이지로 이동//
			stage = (Stage) checkout.getScene().getWindow();
		}
		stage = (Stage) checkout.getScene().getWindow();

	}

	public void ticketchek(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Ticket Chek?");
		alert.setHeaderText("예약한 티켓을 확인하시겠습니까?");
		alert.setContentText("상영시간 이후 환불은 어려울 수 있습니다.");

		if(alert.showAndWait().get()==ButtonType.OK) {//페이지 이동//
			stage = (Stage) checkout.getScene().getWindow();
		}
		stage = (Stage) checkout.getScene().getWindow();

	}

}
