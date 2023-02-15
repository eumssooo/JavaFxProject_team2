package test_1.Service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.SelectMovieController;
import test_1.DAO.MovieDAO;
import test_1.DAO.MovieDAOImpl;
import test_1.View.Customer;
import test_1.View.selData;

public class SelectMovieServiceImpl4 implements SelectMovieService4 {
	Parent selectSession;
	Parent chkInfo;

	private Customer cust;

	private static CommonService cs;
	private static  MovieDAO md;

	int ticketCostAdult = 14000;
	int ticketCostChildren = 11000;

	public SelectMovieServiceImpl4 () {
		cs = new CommonServiceImpl();
		md = new MovieDAOImpl();
	}

	// 상영 시간 계산
	public String movieTime (int a, int b, int c) {
		int movieH = (a * 60 + b +c ) /60;
		int movieM = (a * 60 + b +c ) %60;
		String movieTime = (movieH + ":" + movieM);
		return movieTime;
	}

	@Override
	public void nextPage4(Parent selectPerson, selData sd) {
		// TODO Auto-generated method stub

		// 미성년자가 청소년 관람 불가 영화 선택시 alert 메세지 뜨도록 추가해야함
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
		} else if (md.getAgeLimit(sd.getSelTitle()) == 19){
			if (children_1.isSelected() || children_2.isSelected() || 
					children_3.isSelected() || !children_4.isSelected()) {
				cs.alertMsg("청소년 좌석 선택 불가", "청소년 관람 불가 영화", "청소년 관람 불가 영화입니다.\n청소년 좌석은 선택 불가능합니다.");
				return; 
			}
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

			// 로그인 된 아이디 표시
			Label loginName = (Label) chkInfo.lookup("#loginName");
			loginName.setText(cust.getId() + " 님");

			// 예매 정보 확인 페이지에 들어갈 정보
			// 영화 제목
			Label chkTitle = (Label) chkInfo.lookup("#chkTitle"); 
			chkTitle.setText(sd.getSelTitle());
			// 상영 날짜
			Label chkDate = (Label) chkInfo.lookup("#chkDate"); 
			chkDate.setText(sd.getSelDate());
			// 상영관, 상영 시간
			Label chkRoom = (Label) chkInfo.lookup("#chkRoom"); 
			chkRoom.setText(sd.getSelRoom() + "관 " + sd.getSelTime()); // 상영 시간
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
			sd.setCost((sd.getSelAdultNum()*ticketCostAdult + sd.getSelChildrenNum()*ticketCostChildren));
			cost.setText(sd.getCost() + "원");

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

		// 로그인 된 아이디 표시
		Label loginName = (Label) selectSession.lookup("#loginName");
		loginName.setText(cust.getId() + " 님");

		Label time_1 = (Label) selectSession.lookup("#time_1"); 
		time_1.setText("9:00 - " + movieTime(9,0,md.getMovieRunningTime(sd.getSelTitle())));
		Label time_2 = (Label) selectSession.lookup("#time_2");
		time_2.setText("12:30 - "+ movieTime(12,30,md.getMovieRunningTime(sd.getSelTitle())));
		Label time_3 = (Label) selectSession.lookup("#time_3");
		time_3.setText("16:00 - "+ movieTime(16,0,md.getMovieRunningTime(sd.getSelTitle())));
		Label time_4 = (Label) selectSession.lookup("#time_4");
		time_4.setText("19:30 - "+ movieTime(19,30,md.getMovieRunningTime(sd.getSelTitle())));
		Label time_5 = (Label) selectSession.lookup("#time_5");
		time_5.setText("22:00 - "+ movieTime(22,0,md.getMovieRunningTime(sd.getSelTitle())));
	}


	@Override
	public void colorChange(Parent selectPerson) {
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
			
		if (adult_1.isSelected()) {
			adult_1.setStyle("-fx-background-color:black");
			adult_2.setStyle("-fx-background-color:#e22510");
			adult_3.setStyle("-fx-background-color:#e22510");
			adult_4.setStyle("-fx-background-color:#e22510");
		} else if (adult_2.isSelected()) {
			adult_2.setStyle("-fx-background-color:black");
			adult_1.setStyle("-fx-background-color:#e22510");
			adult_3.setStyle("-fx-background-color:#e22510");
			adult_4.setStyle("-fx-background-color:#e22510");
		} else if (adult_3.isSelected()) {
			adult_3.setStyle("-fx-background-color:black");
			adult_1.setStyle("-fx-background-color:#e22510");
			adult_2.setStyle("-fx-background-color:#e22510");
			adult_4.setStyle("-fx-background-color:#e22510");
		} else if (adult_4.isSelected()) {
			adult_4.setStyle("-fx-background-color:black");
			adult_1.setStyle("-fx-background-color:#e22510");
			adult_2.setStyle("-fx-background-color:#e22510");
			adult_3.setStyle("-fx-background-color:#e22510");
		}
		
		if (children_1.isSelected()) {
			children_1.setStyle("-fx-background-color:black");
			children_2.setStyle("-fx-background-color:#e22510");
			children_3.setStyle("-fx-background-color:#e22510");
			children_4.setStyle("-fx-background-color:#e22510");
		} else if (children_2.isSelected()) {
			children_2.setStyle("-fx-background-color:black");
			children_1.setStyle("-fx-background-color:#e22510");
			children_3.setStyle("-fx-background-color:#e22510");
			children_4.setStyle("-fx-background-color:#e22510");
		} else if (children_3.isSelected()) {
			children_3.setStyle("-fx-background-color:black");
			children_1.setStyle("-fx-background-color:#e22510");
			children_2.setStyle("-fx-background-color:#e22510");
			children_4.setStyle("-fx-background-color:#e22510");
		} else if (children_4.isSelected()) {
			children_4.setStyle("-fx-background-color:black");
			children_1.setStyle("-fx-background-color:#e22510");
			children_2.setStyle("-fx-background-color:#e22510");
			children_3.setStyle("-fx-background-color:#e22510");
		}
		
	}
}
