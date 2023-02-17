package test_1.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.ConfirmController;
import test_1.Controller.SelectMovieController;
import test_1.Controller.checkoutController;
import test_1.DAO.SeatDAO;
import test_1.DAO.SeatDAOImpl;
import test_1.DAO.TicketDAO;
import test_1.DAO.TicketDAOImpl;
import test_1.View.Customer;
import test_1.View.Seat;
import test_1.View.selData;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.DAO.SeatDAO;
import test_1.DAO.SeatDAOImpl;
import test_1.View.Seat;



public class SeatServiceImpl implements SeatService {
	private Parent toMovie;
	private SeatDAO sdao;
	private TicketService ts;
	private CommonService cs;
	private String temp;
	private int cnt = 0;
	private selData sd;
	public static CheckBox[] selarray = new CheckBox[16];
	private TicketDAO td;
	
	// 이전 페이지 정보에 필요
	int ticketCostAdult = 14000;
	int ticketCostChildren = 11000;

	public SeatServiceImpl(){
		sdao = new SeatDAOImpl();
		cs = new CommonServiceImpl();
		ts = new TicketServiceImpl();
		sd = new selData();
		td = new TicketDAOImpl();
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
		ctrl.setSelData(sd);
		s.setTitle("예매 정보 확인");
		s.show();

		// 예매 정보 확인 페이지에 들어갈 정보
		// 영화 제목
		Label chkTitle = (Label) toMovie.lookup("#chkTitle"); 
		chkTitle.setText(sd.getSelTitle());
		// 상영 날짜
		Label chkDate = (Label) toMovie.lookup("#chkDate"); 
		chkDate.setText(sd.getSelDate());
		// 상영관
		Label chkRoom = (Label) toMovie.lookup("#chkRoom"); 
		chkRoom.setText(sd.getSelRoom() + "관 ");
		// 상영 시간
		Label chkTime = (Label) toMovie.lookup("#chkTime"); 
		chkTime.setText(sd.getSelTime());
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
		if(selSeatCnt(sd) < sd.getSelAdultNum() + sd.getSelChildrenNum()) {
			cs.alertMsg("인원 부족", "인원 부족", "인원이 부족합니다.");
			return;
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("좌석 확인");
		alert.setHeaderText(selSeatCnt(sd) +"개 좌석 선택");
		alert.setContentText(sd.getSelSeatNum() + " 좌석이 선택 되었습니다.\n예매하시겠습니까?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){ // ok버튼을 눌렀을 때
			// 다음 창으로 넘어가기
			sd.setReserveDate();
			
			// 추가함
			sd.setUserName(td.getDAO(Customer.getId()));
			sd.setUserId(Customer.getId());

			ts.insertTicketFromSd(sd);
			
			for(int i = 0; i < selarray.length; i++) {
				if(sd.getSelSeatNum().contains(selarray[i].getText())) {
					System.out.println(selarray[i].getText());
					selarray[i].setDisable(true);
				}
			}
			
			System.out.println("sd -> ticket");
	

			
			// db에 상영관&좌석 정보 저장
			// 오라클에서 room 테이블에 상영관 미리 저장해놔야함(참조키)
			sdao.nextPageSeat(sd);
			td.enrollTicket(sd);
			
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
			ctrl.setSelData(sd);

			s.setTitle("티켓 확인");
			s.show();
//		} else {// 취소버튼을 눌렀을 때
//			// ... user chose CANCEL or closed the dialog
//			return;
		}


	}

	@Override
	public void selectSeat(Parent root, selData sd) {
		// TODO Auto-generated method stub
		selarray = searchSeat(root, sd);

		for(int i = 0; i < selarray.length; i++) {
			if(selarray[i].isSelected()) {
				if(cnt >= sd.getSelAdultNum() + sd.getSelChildrenNum()) {
					cs.alertMsg("인원 초과", "인원 초과", "선택한 관람인원 초과입니다.");
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
		if(temp.length() > 0) {
			temp = temp.substring(0, temp.length() - 1);
		}
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

