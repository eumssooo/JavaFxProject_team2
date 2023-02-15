package test_1.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import test_1.Common.CommonServiceImpl;
import test_1.DAO.SeatDAO;
import test_1.DAO.SeatDAOImpl;
import test_1.Service.SeatService;
import test_1.Service.SeatServiceImpl;
import test_1.View.Seat;
import test_1.View.selData;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.DAO.SeatDAO;
import test_1.DAO.SeatDAOImpl;
import test_1.Service.SeatService;
import test_1.Service.SeatServiceImpl;
import test_1.View.Seat;


public class SeatController {
	private Parent seat;
	private Seat s;
	private CommonServiceImpl cs;
	private SeatDAO sdao;
	private SeatService ss;
	private selData sd;
	
	public SeatController() {
		cs = new CommonServiceImpl();
		sdao = new SeatDAOImpl();
		ss = new SeatServiceImpl();
	}
	
	public void setSelData (selData sd) {
		this.sd= sd;
	}

	public void setSeat(Parent seat) {
		// TODO Auto-generated method stub
		this.seat = seat;
	}
		
	public void printSeats() {
	//	ss.printSeat();
	}
	public void toMovieProc() {
		ss.previousPage(seat, sd);
	}

	
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
		
	}
	
	
	public void cancelSeat() {
		cs.alertMsg("좌석 선택", "좌석 취소", "선택한 좌석이 취소 되었습니다.");
		sdao.cancelseat(s);
	
	}

		
	}


		
	


