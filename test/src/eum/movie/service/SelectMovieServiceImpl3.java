package eum.movie.service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
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
	public void selectSession(Parent selectSession) {
		// TODO Auto-generated method stub
		// 상영 회차 선택
				ToggleButton session_1 = (ToggleButton) selectSession.lookup("#session_1");
				ToggleButton session_2 = (ToggleButton) selectSession.lookup("#session_2");
				ToggleButton session_3 = (ToggleButton) selectSession.lookup("#session_3");
				ToggleButton session_4 = (ToggleButton) selectSession.lookup("#session_4");
				ToggleButton session_5 = (ToggleButton) selectSession.lookup("#session_5");		
						

					if (session_1.isSelected()) {
						System.out.println("1회차");
					} else if (session_2.isSelected()) {
						System.out.println("2회차");
					} else if (session_3.isSelected()) {
						System.out.println("3회차");
					} else if (session_4.isSelected()) {
						System.out.println("4회차");
					} else if (session_5.isSelected()) {
						System.out.println("5회차");
					} else {
						cs.alertMsg("상영회차 선택", "상영회차가 선택되지 않았습니다", "상영회차를 선택해주세요");		
						return;
					}
				
				
	}

	@Override
	public void nextPage3(Parent selectSession) {
		// TODO Auto-generated method stub
		
		
		
		// 인원 선택 페이지로 이동
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
	      
	      SelectMovie_4_Person.setTitle("상영 회차 선택");
	      SelectMovie_4_Person.show();
		}
	

	@Override
	public void previousPage3(Parent selectSession) {
		// TODO Auto-generated method stub
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

}
