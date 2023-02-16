package test_1.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import test_1.Service.LoginService;
import test_1.Service.LoginServiceImpl;
import test_1.View.Customer;
import test_1.View.selData;

public class checkoutController {
	@FXML
	private Button logoutButton;

	//	@FXML
	//	private AnchorPane scenePane;
	Stage stage;
	selData sd;
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

		if(alert.showAndWait().get()==ButtonType.OK) { // 로그인 페이지로 이동
			stage = (Stage) checkout.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../login.fxml"));
			Parent root = null;

			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LoginController ctrl = loader.getController();
			ctrl.setRoot(root);

			stage.setScene(new Scene(root));
			stage.setTitle("로그인");
			stage.show();
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
					getClass().getResource("../../SelectMovie_1_Date.fxml"));
			Parent selectDate = null;

			try {
				selectDate = loader.load();

				stage.setScene(new Scene(selectDate));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SelectMovieController ctrl = loader.getController();
			ctrl.setSelectDate(selectDate);

			stage.setTitle("상영 날짜 선택");
			stage.show();

			Label loginName = (Label) selectDate.lookup("#loginName");
			loginName.setText(Customer.getId() + " 님");

			// 상영날짜 선택 콤보박스 내용 입력 (로그인 이후에 넣기)
			ComboBox<String> cmbDate = (ComboBox<String>) selectDate.lookup("#cmbDate");
			// 현재 날짜부터 5일 선택 가능
			Calendar cal = new GregorianCalendar();	
			SimpleDateFormat formatter = new SimpleDateFormat("MM월 dd일");
			Date date = new Date();
			date = cal.getTime();
			for (int i= 0;i<5;i++) {
				cal.add(Calendar.DATE,i);
				date = cal.getTime();
				cmbDate.getItems().add(formatter.format(date));
				cal.add(Calendar.DATE, -i);
			}

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
			ctrl.setSelData(sd);

			stage.setScene(new Scene(root));
			stage.setTitle("티켓 확인");
			stage.show();
		}
		//		stage = (Stage) checkout.getScene().getWindow();

	}

	public void setSelData(selData sd) {
		// TODO Auto-generated method stub
		this.sd = sd;
	}

}
