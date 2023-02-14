package eum.movie.service;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import eum.movie.Movie;
import eum.movie.SelectMovieController;
import eum.movie.selData;
import eum.movie.dao.MovieDAO;
import eum.movie.dao.MovieDAOImpl;

public class SelectMovieServiceImpl2 implements SelectMovieService2{

	private static CommonService cs;
	private static MovieDAO md;
	Parent root;
	Parent selectSession;
	Movie m = new Movie();
	
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
		
		cmbMovie.getSelectionModel().selectedItemProperty().addListener
		( (v, oldValue, newValue) -> System.out.println(newValue));

		// 콤보박스에서 선택된 값 가져오기
				
		if(cmbMovie.getValue() != null) {// null이 아니면 다음 페이지 접속 가능	
			if(cmbMovie.getValue().equals("타이타닉")) {
				sd.setSelTitle(cmbMovie.getValue());
				System.out.println(md.movieRunningTime(sd.getSelTitle()));				
			} else if(cmbMovie.getValue().equals("더 퍼스트 슬램덩크")) {
				sd.setSelTitle(cmbMovie.getValue());
				
			} else if(cmbMovie.getValue().equals("아바타 물의 길")) {
				sd.setSelTitle(cmbMovie.getValue());
				System.out.println(md.movieRunningTime(sd.getSelTitle()));
			} else if(cmbMovie.getValue().equals("바빌론")) {
				sd.setSelTitle(cmbMovie.getValue());
				System.out.println(md.movieRunningTime(sd.getSelTitle()));
			}

				// 상영회차 선택 페이지(다음 페이지) 로드
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
				 // 상영 시간 표시
				Label time_1 = (Label) selectSession.lookup("#time_1"); 
				time_1.setText("9:00 - " + movieTime(9,0,md.movieRunningTime(sd.getSelTitle())));
				System.out.println(md.movieRunningTime(sd.getSelTitle()));
				Label time_2 = (Label) selectSession.lookup("#time_2");
				time_2.setText("12:30 - "+ movieTime(12,30,md.movieRunningTime(sd.getSelTitle())));
				Label time_3 = (Label) selectSession.lookup("#time_3");
				time_3.setText("16:00 - "+ movieTime(16,0,md.movieRunningTime(sd.getSelTitle())));
				Label time_4 = (Label) selectSession.lookup("#time_4");
				time_4.setText("19:30 - "+ movieTime(19,30,md.movieRunningTime(sd.getSelTitle())));
				Label time_5 = (Label) selectSession.lookup("#time_5");
				time_5.setText("22:00 - "+ movieTime(22,0,md.movieRunningTime(sd.getSelTitle())));
				
				
				
				
			} else if (cmbMovie.getValue() == null) // null값일시 에러 메세지
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
			root = loader.load();

			SelectMovie_1_Date.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}

		SelectMovieController ctrl = loader.getController();


		ctrl.setRoot(root);

		SelectMovie_1_Date.setTitle("상영 날짜 선택");
		SelectMovie_1_Date.show();
		// 선택된 데이터 지우기
		sd.setSelDate(null);
		
		// 상영날짜 선택 콤보박스 내용 입력 (로그인 이후에 넣기)
				ComboBox<String> cmbDate = (ComboBox<String>) root.lookup("#cmbDate");
				cmbDate.getItems().addAll("2월 15일","2월 16일","2월 17일","2월 18일","2월 19일");
				
	}
}
