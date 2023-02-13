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
	public void checkMovieInfo(Parent root) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void previousPage5(Parent chkInfo) {
		// TODO Auto-generated method stub
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

		SelectMovie_4_Person.setTitle("관람 인원 선택");
		SelectMovie_4_Person.show();
	}

}
