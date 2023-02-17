package test_1.Controller;

import java.io.IOException;
import java.util.ArrayList;

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
	private Parent root;
	private Ticket t = new Ticket();
	private CommonService cs;
	private TicketDAO td;
	private TicketService ts;
	selData sd;
	
	public ConfirmController() {
		cs = new CommonServiceImpl();
		td = new TicketDAOImpl();
		ts = new TicketServiceImpl();
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
	public void setSelData(selData sd) {
		this.sd = sd;
	}
	
	// 예매 내역 UI 출력
	public void printTickets() {
		sd.print_selData();
		System.out.println("프린트티켓 직전");
		ts.printTicket(root ,sd); // 이게 테이블뷰 출력되는거
		System.out.println("인서트 직전");
		ts.insertTicketFromSd(sd);
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
		ctrl.setSelData(sd);
		
		s.setTitle("티켓 확인");
		s.show();
	}
	
	// 나머지는 다른 controller에서
	public void cancelTicket() {
		// t에 저장된거 없음
		
//		// t에 sd값을 넣어서 저장해줘서 삭제도 지금 선택한 좌석만 삭제됨
//		t.setUserId(sd.getUserId());
//		t.setSeatNum(sd.getSelSeatNum());
//		t.setRoomNum(sd.getSelRoom());
//		t.setMovieName(sd.getSelTitle());
//		t.setDay(sd.getSelDate());
//		t.setTime(sd.getSelTime());
		
		// 선택된 테이블뷰의 값을 가져옴(인덱스 → 해당 array 찾아서)
		Ticket select = ts.cancelTickets();
		t.setUserId(sd.getUserId()); // select에서 안받아져서 sd 사용(어차피 아이디 같아야함)
		t.setSeatNum(select.getSeatNum());
		t.setRoomNum(select.getRoomNum());
		t.setMovieName(select.getMovieName());
		t.setDay(select.getDay());
		t.setTime(select.getTime());
		
//		System.out.println(select.getSeatNum()); // 잘 나옴
//		System.out.println(select.getMovieName()); // 잘 나옴
//		System.out.println(t.getSeatNum());// 잘 나옴
//		System.out.println(t.getMovieName());// 잘 나옴
//		
//		// 안뜸
//		System.out.println(select.getUserId());
//		System.out.println(t.getUserId());
		
		td.cancelTicket(t);
		td.cancelSeat(t);
		

	}
}
