package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class SeatController {
	private static Parent root;
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

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
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


		
	


