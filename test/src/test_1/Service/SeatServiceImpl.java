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
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.DAO.SeatDAO;
import test_1.DAO.SeatDAOImpl;
import test_1.View.Seat;



public class SeatServiceImpl implements SeatService {
		private Parent toMovie;
		private Seat s;
		private ArrayList<Seat>seats;
		private SeatDAO sdao;
		private CommonService cs;
		
		// 이전 페이지 정보에 필요
		Parent chkInfo;
		int ticketCostAdult = 14000;
		int ticketCostChildren = 11000;
		
		public SeatServiceImpl(){
			sdao = new SeatDAOImpl();
			cs = new CommonServiceImpl();
		}
		
		// 이전페이지로(선택정보 확인) 이동
		@Override
		public void previousPage(Parent seat, selData sd) {
			// TODO Auto-generated method stub
			Stage s = (Stage) seat.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../SelectMovie_5_chkInfo.fxml"));

			try {
				toMovie = loader.load();

				s.setScene(new Scene(toMovie));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SelectMovieController ctrl = loader.getController();

			ctrl.setCheckInfo(toMovie);
			s.setTitle("관람 인원 선택");
			s.show();
		}


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
	
