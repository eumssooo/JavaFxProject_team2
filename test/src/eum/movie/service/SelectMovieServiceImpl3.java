package eum.movie.service;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import eum.movie.Movie;
import eum.movie.SelectMovieController;
import eum.movie.selData;
import eum.movie.dao.MovieDAO;
import eum.movie.dao.MovieDAOImpl;

public class SelectMovieServiceImpl3 implements SelectMovieService3 {

	Parent selectMovie;
	Parent selectPerson;
	Movie m = new Movie();
	selData sd = new selData();

	private static CommonService cs;
	private static  MovieDAO md;

	public SelectMovieServiceImpl3 () {
		cs = new CommonServiceImpl();
		md = new MovieDAOImpl();
	}

	@Override
	public void nextPage3(Parent selectSession) {
		// TODO Auto-generated method stub
		// 상영 회차 선택 토글버튼
		ToggleButton session_1 = (ToggleButton) selectSession.lookup("#session_1");
		ToggleButton session_2 = (ToggleButton) selectSession.lookup("#session_2");
		ToggleButton session_3 = (ToggleButton) selectSession.lookup("#session_3");
		ToggleButton session_4 = (ToggleButton) selectSession.lookup("#session_4");
		ToggleButton session_5 = (ToggleButton) selectSession.lookup("#session_5");		

		//  선택한 상영회차 입력
		if (session_1.isSelected()) {
			sd.setSelSession(1);
			System.out.println(sd.getSelSession());
		} else if (session_2.isSelected()) {
			sd.setSelSession(2);
			System.out.println(sd.getSelSession());
		} else if (session_3.isSelected()) {
			sd.setSelSession(3);
			System.out.println(sd.getSelSession());
		} else if (session_4.isSelected()) {
			sd.setSelSession(4);
			System.out.println(sd.getSelSession());
		} else if (session_5.isSelected()) {
			sd.setSelSession(5);
			System.out.println(sd.getSelSession());
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
	public void previousPage3(Parent selectSession) {
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
	}


