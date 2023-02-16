package test_1.Service;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.SelectMovieController;
import test_1.DAO.MovieDAO;
import test_1.DAO.MovieDAOImpl;
import test_1.View.Customer;
import test_1.View.selData;

public class SelectMovieServiceImpl2 implements SelectMovieService2{

	private static CommonService cs;
	private static MovieDAO md;
	Parent selectDate;
	Parent selectSession;

	private Customer cust;

	public SelectMovieServiceImpl2 () {
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
	public void nextPage2(Parent selectMovie, selData sd) {
		// TODO Auto-generated method stub


		ComboBox<String> cmbMovie = (ComboBox<String>) selectMovie.lookup("#cmbMovie");


		// 콤보박스에서 선택된 값 가져오기

		if(cmbMovie.getValue() != null) {// null이 아니면 다음 페이지 접속 가능	
			if(cmbMovie.getValue().equals("타이타닉")) {
				sd.setSelTitle(cmbMovie.getValue());
				sd.setSelRoom(1);
			} else if(cmbMovie.getValue().equals("더 퍼스트 슬램덩크")) {
				sd.setSelTitle(cmbMovie.getValue());		
				sd.setSelRoom(2);
			} else if(cmbMovie.getValue().equals("아바타 물의 길")) {
				sd.setSelTitle(cmbMovie.getValue());
				sd.setSelRoom(3);
			} else if(cmbMovie.getValue().equals("바빌론")) {
				sd.setSelTitle(cmbMovie.getValue());
				sd.setSelRoom(4);
			}

			// 상영회차 선택 페이지(다음 페이지) 로드

			// 1. 청소년이 청불 영화를 선택한 경우 return;
			// 2. 청소년이 청불이 아닌 관람등급이 높은 영화를 선택한 경우 alert후 진행
			// 3. 영화 미선택시 에러
			if (md.getMemberAge(cust.getId())<=19) {
				if (md.getAgeLimit(sd.getSelTitle()) == 19) {
					cs.alertMsg("관람 등급", "청소년 관람 불가 영화", "청소년 관람 불가 영화입니다.");
					return;
				} else {
					if (md.getMemberAge(cust.getId()) <= md.getAgeLimit(sd.getSelTitle())){
						cs.alertMsg("관람 등급", md.getAgeLimit(sd.getSelTitle()) + "세 이상 관람가", "관람에 지도가 필요한 영화입니다.");
					}
				}
			}				
			Stage SelectMovie_3_Session = (Stage) selectMovie.getScene().getWindow();
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
			ctrl.setSelData(sd);

			SelectMovie_3_Session.setTitle("상영 회차 선택");
			SelectMovie_3_Session.show();

			// 로그인 된 아이디 표시
			Label loginName = (Label) selectSession.lookup("#loginName");
			loginName.setText(cust.getId() + " 님");

			// 상영 시간 표시
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

		if (cmbMovie.getValue() == null) // null값일시 에러 메세지
		{
			cs.alertMsg("영화 선택", "영화가 선택되지 않았습니다", "영화를 선택해주세요");
			cmbMovie.requestFocus();
			return;
		}
	}

	@Override
	public void previousPage2(Parent selectMovie, selData sd) {
		// TODO Auto-generated method stub

		// 상영 날짜 선택 페이지 (이전 페이지) 로드
		Stage SelectMovie_1_Date = (Stage) selectMovie.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../SelectMovie_1_Date.fxml"));

		try {
			selectDate = loader.load();

			SelectMovie_1_Date.setScene(new Scene(selectDate));
		} catch (Exception e) {
			e.printStackTrace();
		}

		SelectMovieController ctrl = loader.getController();


		ctrl.setSelectDate(selectDate);

		SelectMovie_1_Date.setTitle("상영 날짜 선택");
		SelectMovie_1_Date.show();
		// 선택된 데이터 지우기
		sd.setSelDate(null);

		// 로그인 된 아이디 표시
		Label loginName = (Label) selectDate.lookup("#loginName");
		loginName.setText(cust.getId() + " 님");

		// 상영날짜 선택 콤보박스 내용 입력 
		ComboBox<String> cmbDate = (ComboBox<String>) selectDate.lookup("#cmbDate");
		// 현재 날짜부터 5일 선택 가능
		Calendar cal = new GregorianCalendar();	
		SimpleDateFormat formatter = new SimpleDateFormat("MM월 dd일");
		Date date = new Date();
		date = cal.getTime();
		for (int i= 0;i<4;i++) {
			cal.add(Calendar.DATE,i);
			date = cal.getTime();
			cmbDate.getItems().add(formatter.format(date));
			cal.add(Calendar.DATE, -i);
		}

	}
}
