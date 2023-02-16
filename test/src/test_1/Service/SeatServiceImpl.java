package test_1.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	private String temp;
	private int cnt = 0;

	// 이전 페이지 정보에 필요
	int ticketCostAdult = 14000;
	int ticketCostChildren = 11000;

	public SeatServiceImpl(){
		sdao = new SeatDAOImpl();
		cs = new CommonServiceImpl();
	}
	
	@Override
	public int selSeatCnt(selData sd){
		String[] token = sd.getSelSeatNum().split("/");
		int tokCnt = 0;
		
		return tokCnt = token.length;
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
	public void NextPage(Parent seat, selData sd) {
		// TODO Auto-generated method stub
		
		
		cs.alertMsg("좌석 확인", selSeatCnt(sd) +"개 좌석 선택", temp+" 좌석이 선택 되었습니다");

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

	@Override
	public void selectSeat(Parent root, selData sd) {
		// TODO Auto-generated method stub
		CheckBox[] selarray = new CheckBox[16];
		selarray = searchSeat(root, sd);
		
		for(int i = 0; i < selarray.length; i++) {
			if(selarray[i].isSelected()) {
				if(cnt >= sd.getSelAdultNum() + sd.getSelChildrenNum()) {
					cs.alertMsg("인원 초과", "인원 초과", "인원 초과에욥!!!");
					selarray[i].requestFocus();
					// 마지막으로 선택된 체크박스를 가져와야 함
					selarray[i].setSelected(false);
					cnt--;
					continue;
				}
				cnt++;
				temp += selarray[i].getText() + "/";
				// 잔여 좌석수 추가

				// selSeatNum은 나중에
				// 나중에 StringTokenizer 로 나눠쓰면 됌.
			}
		}
		temp = temp.substring(0, temp.length() - 1);
		sd.setSelSeatNum(temp);
		cnt=0;
		temp = "";
		System.out.println(sd.getSelSeatNum());
	}

	@Override
	public CheckBox[] searchSeat(Parent root, selData sd) {
		// TODO Auto-generated method stub
		String sel = "#chk";
		CheckBox[] selarray = new CheckBox[16];
		temp = "";

		for(int i = 0; i<selarray.length; i++) {
			sel += i+1;
			selarray[i] = (CheckBox)root.lookup(sel);
			sel = "#chk";
		}
		
		return selarray;
	}

}

