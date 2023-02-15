package test_1.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import test_1.View.*;
import test_1.Service.*;
import test_1.Common.*;
import test_1.DAO.*;

public class ConfirmController {
	private static Parent root;
	private static Ticket t;
	private static CommonService cs;
	private static TicketDAO td;
	private static TicketService ts;
	@FXML
    private Label lblTest;
	
	public ConfirmController() {
		cs = new CommonServiceImpl();
		td = new TicketDAOImpl();
		ts = new TicketServiceImpl();
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
	
	// 예매 내역 UI 출력
	public void printTickets() {
		ts.printTicket(root ,t);
	}

	// 돌아가기 ( 전 화면으로 가도록 수정해야 함)
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
		
		Stage s = (Stage) root.getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../ticketcheck.fxml"));
		Parent checkout = null;
		
		try {
			checkout = loader.load();
			
			s.setScene(new Scene(checkout));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		checkoutController ctrl = loader.getController();
		ctrl.setCheckout(checkout);
		
		s.setTitle("티켓 확인");
		s.show();
	}
	
	// 나머지는 다른 controller에서
	public void cancelTicket() {
		// 정말로 티켓을 취소하시겠습니까?
		cs.alertMsg("티켓 취소", "티켓 취소", "티켓이 취소 되었습니다.");
		td.cancelTicket(t);
	}
}
