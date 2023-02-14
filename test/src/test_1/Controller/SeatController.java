package test_1.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.DAO.SeatDAO;
import test_1.DAO.SeatDAOImpl;
import test_1.Service.SeatService;
import test_1.Service.SeatServiceImpl;
import test_1.View.Seat;

public class SeatController {
	private static Parent seat;
	private static Seat s;
	private static CommonService cs;
	private static SeatDAO sd;
	private static SeatService ss;
	
	@FXML
    private Label lblTest;
	
	public SeatController() {
		cs = new CommonServiceImpl();
		sd = new SeatDAOImpl();
		ss = new SeatServiceImpl();
	}

	public void setSeat(Parent seat) {
		// TODO Auto-generated method stub
		this.seat = seat;
	}
	
	
	public void printSeats() {
		ss.printSeat();
	}

	
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
		
	}
	
	
	public void cancelSeat() {
		cs.alertMsg("자석 취소", "자석 취소", "자석이 취소 되었습니다.");
		sd.cancelseat(s);
	
	}

		
	}


		
	


