package test_1.Service;


import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.SelectMovieController;
import test_1.DAO.SeatDAO;
import test_1.DAO.SeatDAOImpl;
import test_1.View.Seat;
import test_1.View.selData;


public class SeatServiceImpl implements SeatService {
		private Seat seat;
		private ArrayList<Seat>seats;
		private SeatDAO sd;
		private CommonService cs;
		
		// 이전 페이지 정보에 필요
		Parent chkInfo;
		int ticketCostAdult = 14000;
		int ticketCostChildren = 11000;
		
		public SeatServiceImpl(){
			sd = new SeatDAOImpl();
			cs = new CommonServiceImpl();
		}
		
		// 이전페이지로(선택정보 확인) 이동
		//@Override
		//public void previousPage() {
		//	// TODO Auto-generated method stub
			
		//}


		@Override
		public void printSeat(Parent root) {
			// TODO Auto-generated method stub
			// 선택된 좌석 갯수
			Label title = (Label)root.lookup("#confirmTitle");			
			title.setText("님의 예매 내역");
		}



		@Override
		public void joinSeat(Parent root) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public boolean SeatServiceChk(String text, String text2) {
			// TODO Auto-generated method stub
			return false;
		}


}
	
