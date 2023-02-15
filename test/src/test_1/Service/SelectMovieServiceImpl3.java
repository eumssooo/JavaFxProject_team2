package test_1.Service;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.SelectMovieController;
import test_1.DAO.MovieDAO;
import test_1.DAO.MovieDAOImpl;
import test_1.View.selData;

public class SelectMovieServiceImpl3 implements SelectMovieService3 {

	Parent selectMovie;
	Parent selectPerson;


	private static CommonService cs;
	private static MovieDAO md;

	public SelectMovieServiceImpl3 () {
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
	public void nextPage3(Parent selectSession, selData sd) {
		// TODO Auto-generated method stub

		// 상영 회차 선택 토글버튼
		ToggleButton session_1 = (ToggleButton) selectSession.lookup("#session_1");
		ToggleButton session_2 = (ToggleButton) selectSession.lookup("#session_2");
		ToggleButton session_3 = (ToggleButton) selectSession.lookup("#session_3");
		ToggleButton session_4 = (ToggleButton) selectSession.lookup("#session_4");
		ToggleButton session_5 = (ToggleButton) selectSession.lookup("#session_5");		

		//  선택한 상영회차 입력
		if (session_1.isSelected()) {
			sd.setSelTime("9:00 - " + movieTime(9,0,md.movieRunningTime(sd.getSelTitle())));
		} else if (session_2.isSelected()) {
			sd.setSelTime("12:30 - "+ movieTime(12,30,md.movieRunningTime(sd.getSelTitle())));
		} else if (session_3.isSelected()) {
			sd.setSelTime("16:00 - "+ movieTime(16,0,md.movieRunningTime(sd.getSelTitle())));
		} else if (session_4.isSelected()) {
			sd.setSelTime("19:30 - "+ movieTime(19,30,md.movieRunningTime(sd.getSelTitle())));
		} else if (session_5.isSelected()) {
			sd.setSelTime("22:00 - "+ movieTime(22,0,md.movieRunningTime(sd.getSelTitle())));
		} else {
			cs.alertMsg("상영회차 선택", "상영회차가 선택되지 않았습니다", "상영회차를 선택해주세요");		
			return;
		}

		// 인원 선택 페이지 (다음 페이지) 로드
		Stage SelectMovie_4_Person = (Stage) selectSession.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../SelectMovie_4_Person.fxml"));

		try {
			selectPerson = loader.load();

			SelectMovie_4_Person.setScene(new Scene(selectPerson));
		} catch (Exception e) {
			e.printStackTrace();
		}

		SelectMovieController ctrl = loader.getController();

		ctrl.setSelectPerson(selectPerson);
		ctrl.setSelData(sd);

		SelectMovie_4_Person.setTitle("상영 회차 선택");
		SelectMovie_4_Person.show();
	}

	@Override
	public void previousPage3(Parent selectSession, selData sd) {
		// TODO Auto-generated method stub
		// 영화 선택 페이지 (이전 페이지) 로드
		Stage SelectMovie_2_Movie = (Stage) selectSession.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../SelectMovie_2_Movie.fxml"));

		try {
			selectMovie = loader.load();

			SelectMovie_2_Movie.setScene(new Scene(selectMovie));
		} catch (Exception e) {
			e.printStackTrace();
		}

		SelectMovieController ctrl = loader.getController();

		ctrl.setSelectMovie(selectMovie);

		SelectMovie_2_Movie.setTitle("영화 선택");
		SelectMovie_2_Movie.show();

		// 데이터 지우기
		sd.setSelTitle(null);

		// 영화 선택 콤보박스
		ComboBox<String> cmbMovie = (ComboBox<String>) selectMovie.lookup("#cmbMovie");
		cmbMovie.getItems().addAll("타이타닉","더 퍼스트 슬램덩크","아바타 물의 길","바빌론");

		// 영화 포스터
		ImageView imgMovie = (ImageView) selectMovie.lookup("#imgMovie");
		// 콤보박스 클릭시 데이터 확인
		cmbMovie.getSelectionModel().selectedItemProperty().addListener
		( (v, oldValue, newValue) ->
		imgMovie.setImage
		(new Image(getClass().getResource("/image/"+newValue+".jpg").toExternalForm())));

	}
}
