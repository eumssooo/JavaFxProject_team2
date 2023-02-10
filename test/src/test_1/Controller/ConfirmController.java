package test_1.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
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
	
	public ConfirmController() {
		cs = new CommonServiceImpl();
		td = new TicketDAOImpl();
		ts = new TicketServiceImpl();
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}

	// 돌아가기 ( 전 화면으로 가도록 수정해야 함)
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
		
		//		Stage complete(결제완료 화면) = (Stage) root.getScene().getWindow();
		//		FXMLLoader loader = new FXMLLoader(
		//		getClass().getResource("../../complete(결제완료화면).fxml"));
	}
	
	// 나머지는 다른 controller에서
	public void cancelTicket() {
		// 정말로 티켓을 취소하시겠습니까?
		cs.alertMsg("티켓 취소", "티켓 취소", "티켓이 취소 되었습니다.");
		td.cancelTicket(t);
	}
}
