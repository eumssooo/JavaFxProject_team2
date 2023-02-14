package eum.movie.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import eum.movie.Movie;
import eum.movie.SelectMovieController;
import eum.movie.selData;
import eum.movie.dao.MovieDAO;
import eum.movie.dao.MovieDAOImpl;

public class SelectMovieServiceImpl4 implements SelectMovieService4 {
	Parent selectSession;
	Parent chkInfo;

	private static CommonService cs;
	private static  MovieDAO md;

	int ticketCostAdult = 14000;
	int ticketCostChildren = 11000;

	Movie m = new Movie();

	public SelectMovieServiceImpl4 () {
		cs = new CommonServiceImpl();
		md = new MovieDAOImpl();
	}

	@Override
	public void nextPage4(Parent selectPerson, selData sd) {
		// TODO Auto-generated method stub


		// 인원선택 토글버튼 - 성인
		ToggleButton adult_1 = (ToggleButton) selectPerson.lookup("#adult_1");
		ToggleButton adult_2 = (ToggleButton) selectPerson.lookup("#adult_2");
		ToggleButton adult_3 = (ToggleButton) selectPerson.lookup("#adult_3");
		ToggleButton adult_4 = (ToggleButton) selectPerson.lookup("#adult_4");
		// 인원선택 토글버튼 - 청소년
		ToggleButton children_1 = (ToggleButton) selectPerson.lookup("#children_1");
		ToggleButton children_2 = (ToggleButton) selectPerson.lookup("#children_2");
		ToggleButton children_3 = (ToggleButton) selectPerson.lookup("#children_3");
		ToggleButton children_4 = (ToggleButton) selectPerson.lookup("#children_4");

		// 관람 인원이 선택되지 않은 경우 메세지
		if (!adult_1.isSelected() && !adult_2.isSelected() && !adult_3.isSelected() && !adult_4.isSelected() &&
				!children_1.isSelected()&& !children_2.isSelected() && !children_3.isSelected() &&
				!children_4.isSelected()) {	
			cs.alertMsg("관람 인원 선택", "관람 인원이 선택되지 않았습니다", "관람 인원을 선택해주세요");	
			return;
		} else { // 관람인원 값
			// 성인
			if (adult_1.isSelected()) {
				sd.setSelAdultNum(1);
				System.out.println(sd.getSelAdultNum());
			} else if (adult_2.isSelected()) {
				sd.setSelAdultNum(2);
				System.out.println(sd.getSelAdultNum());
			} else if (adult_3.isSelected()) {
				sd.setSelAdultNum(3);
				System.out.println(sd.getSelAdultNum());
			} else if (adult_4.isSelected()) {
				sd.setSelAdultNum(4);
				System.out.println(sd.getSelAdultNum());
			}
			// 청소년
			if (children_1.isSelected()) {
				sd.setSelChildrenNum(1);
				System.out.println(sd.getSelChildrenNum());
			} else if (children_2.isSelected()) {
				sd.setSelChildrenNum(2);
				System.out.println(sd.getSelChildrenNum());
			} else if (children_3.isSelected()) {
				sd.setSelChildrenNum(3);
				System.out.println(sd.getSelChildrenNum());
			} else if (children_4.isSelected()) {
				sd.setSelChildrenNum(4);
				System.out.println(sd.getSelChildrenNum());
			}

			// 티켓 확인 페이지 (다음페이지) 로드
			Stage SelectMovie_5_chkInfo = (Stage) selectPerson.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../SelectMovie_5_chkInfo.fxml"));
			try {
				chkInfo = loader.load();

				SelectMovie_5_chkInfo.setScene(new Scene(chkInfo));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SelectMovieController ctrl = loader.getController();
			ctrl.setCheckInfo(chkInfo);
			ctrl.setSelData(sd);

			SelectMovie_5_chkInfo.setTitle("예매 정보 확인");
			SelectMovie_5_chkInfo.show();
			
			// 예매 정보 확인 페이지에 들어갈 정보
			// 영화 제목
			Label chkTitle = (Label) chkInfo.lookup("#chkTitle"); 
			chkTitle.setText(sd.getSelTitle());
			// 상영 날짜
			Label chkDate = (Label) chkInfo.lookup("#chkDate"); 
			chkDate.setText(sd.getSelDate());
			// 상영관, 상영 시간
			Label chkRoom = (Label) chkInfo.lookup("#chkRoom"); 
			chkRoom.setText("" + sd.getSelSession()); // 회차에 연결된 상영관 값 연결
			// 잔여 좌석
			// Label chkSeat = (Label) chkInfo.lookup("#chkSeat"); 
			// chkSeat.setText("잔여 좌석");
			// 관람 인원
			Label chkPersonNum = (Label) chkInfo.lookup("#chkPersonNum"); 
			// 선택 인원 표시
			if(sd.getSelAdultNum() != 0 && sd.getSelChildrenNum() != 0) {
				chkPersonNum.setText("성인 " + sd.getSelAdultNum() + "명 청소년" + sd.getSelChildrenNum() +"명");
			}else if(sd.getSelChildrenNum() != 0) {
				chkPersonNum.setText("청소년 " + sd.getSelChildrenNum() + "명");
			}else if(sd.getSelAdultNum() != 0) {
				chkPersonNum.setText("성인 " + sd.getSelAdultNum() + "명");
			}
			// 총 금액
			Label cost = (Label) chkInfo.lookup("#cost"); 
			cost.setText((sd.getSelAdultNum()*ticketCostAdult + sd.getSelChildrenNum()*ticketCostChildren) + "원");
			
			//값 잘 넘어오는지 확인용
			System.out.println(sd.getSelDate());
			System.out.println(sd.getSelTitle());
			System.out.println(sd.getSelSession());
			System.out.println(sd.getSelAdultNum());
			System.out.println(sd.getSelChildrenNum());

		}
	}

	@Override
	public void previousPage4(Parent selectPerson, selData sd) {
		// TODO Auto-generated method stub
		// 상영회차 선택(이전 페이지)으로 이동
		Stage SelectMovie_3_Session = (Stage) selectPerson.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../SelectMovie_3_Session.fxml"));

		try {
			selectSession = loader.load();

			SelectMovie_3_Session.setScene(new Scene(selectSession));
		} catch (Exception e) {
			e.printStackTrace();
		}

		SelectMovieController ctrl = loader.getController();


		ctrl.setSelectSession(selectSession);

		SelectMovie_3_Session.setTitle("상영 회차 선택");
		SelectMovie_3_Session.show();
	}

}
