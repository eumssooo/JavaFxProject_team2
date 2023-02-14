package test_1.Service;


import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.DAO.SeatDAO;
import test_1.DAO.SeatDAOImpl;
import test_1.View.Seat;


public class SeatServiceImpl implements SeatService {
		private Seat seat;
		private ArrayList<Seat>seats;
		private SeatDAO sd;
		private CommonService cs;
		
		public SeatServiceImpl(){
			sd = new SeatDAOImpl();
			cs = new CommonServiceImpl();
		}

		
		public void printSeat(Parent root) {
			// TODO Auto-generated method stub
			Label title = (Label)root.lookup("#confirmTitle");			
			title.setText("님의 예매 내역");
		}


		@Override
		public void printSeat() {
			// TODO Auto-generated method stub
			
		

			
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
	
