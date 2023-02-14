package eum.movie.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import eum.movie.Movie;
import eum.movie.SelectMovieController;
import eum.movie.selData;
import eum.movie.dao.MovieDAO;
import eum.movie.dao.MovieDAOImpl;

public class SelectMovieServiceImpl5 implements SelectMovieService5{

	Parent selectPerson;
	Movie m = new Movie();
	selData sd = new selData();

	private static CommonService cs;
	private static  MovieDAO md;

	public SelectMovieServiceImpl5 () {
		cs = new CommonServiceImpl();
		md = new MovieDAOImpl();
	}


	@Override
	public void checkMovieInfo(Parent chkInfo,selData sd) { // 좌석선택 화면으로 넘기기
		// TODO Auto-generated method stub
		// Stage 좌석선택화면 = (Stage) chkInfo.getScene().getWindow();
		// FXMLLoader loader = new FXMLLoader(
		//		getClass().getResource("../../좌석선택.fxml"));
		// try {
		// 	(좌석 선택 Parent)  = loader.load();

		//	좌석선택화면.setScene(new Scene(좌석 선택 Parent));
		// } catch (Exception e) {
		//	e.printStackTrace();
		// }
		
	}

	@Override
	public void previousPage5(Parent chkInfo, selData sd) {
		// TODO Auto-generated method stub
		// 관람 인원 선택 페이지 (이전 페이지) 로드
		Stage SelectMovie_4_Person = (Stage) chkInfo.getScene().getWindow();
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
		sd.setSelAdultNum(0);
		sd.setSelChildrenNum(0);

		SelectMovie_4_Person.setTitle("관람 인원 선택");
		SelectMovie_4_Person.show();
	}

}
