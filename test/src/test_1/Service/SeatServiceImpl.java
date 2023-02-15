package test_1.Service;


import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.SelectMovieController;
import test_1.Controller.checkoutController;
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
		s.setTitle("예매 정보 확인");
		s.show();

		// 예매 정보 확인 페이지에 들어갈 정보
		// 영화 제목
		Label chkTitle = (Label) toMovie.lookup("#chkTitle"); 
		chkTitle.setText(sd.getSelTitle());
		// 상영 날짜
		Label chkDate = (Label) toMovie.lookup("#chkDate"); 
		chkDate.setText(sd.getSelDate());
		// 상영관, 상영 시간
		Label chkRoom = (Label) toMovie.lookup("#chkRoom"); 
		chkRoom.setText(sd.getSelRoom() + "관 " + sd.getSelTime()); // 상영 시간
		// 잔여 좌석
		// Label chkSeat = (Label) chkInfo.lookup("#chkSeat"); 
		// chkSeat.setText("잔여 좌석");
		// 관람 인원
		Label chkPersonNum = (Label) toMovie.lookup("#chkPersonNum"); 
		// 선택 인원 표시
		if(sd.getSelAdultNum() != 0 && sd.getSelChildrenNum() != 0) {
			chkPersonNum.setText("성인 " + sd.getSelAdultNum() + "명 청소년" + sd.getSelChildrenNum() +"명");
		}else if(sd.getSelChildrenNum() != 0) {
			chkPersonNum.setText("청소년 " + sd.getSelChildrenNum() + "명");
		}else if(sd.getSelAdultNum() != 0) {
			chkPersonNum.setText("성인 " + sd.getSelAdultNum() + "명");
		}
		// 총 금액
		Label cost = (Label) toMovie.lookup("#cost"); 
		cost.setText((sd.getSelAdultNum()*ticketCostAdult + sd.getSelChildrenNum()*ticketCostChildren) + "원");
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

	@Override
	public void NextPage(Parent seat) {
		// TODO Auto-generated method stub

		cs.alertMsg(null, null, null);
		
		
		
		
		// 다음 창으로 넘어가기
		Stage s = (Stage) seat.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../ticketcheck.fxml")); //경로 수정

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


}

