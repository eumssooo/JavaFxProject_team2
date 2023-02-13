package eum.movie.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	Movie m = new Movie();
	selData sd;
	

	public SelectMovieServiceImpl4 () {
		cs = new CommonServiceImpl();
		md = new MovieDAOImpl();
		sd = new selData();
	}


	@Override
	public void selectPerson(Parent root) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextPage4(Parent selectPerson) {
		// TODO Auto-generated method stub
		

		ToggleButton adult_1 = (ToggleButton) selectPerson.lookup("#adult_1");
		ToggleButton adult_2 = (ToggleButton) selectPerson.lookup("#adult_2");
		ToggleButton adult_3 = (ToggleButton) selectPerson.lookup("#adult_3");
		ToggleButton adult_4 = (ToggleButton) selectPerson.lookup("#adult_4");

		ToggleButton children_1 = (ToggleButton) selectPerson.lookup("#children_1");
		ToggleButton children_2 = (ToggleButton) selectPerson.lookup("#children_2");
		ToggleButton children_3 = (ToggleButton) selectPerson.lookup("#children_3");
		ToggleButton children_4 = (ToggleButton) selectPerson.lookup("#children_4");



		if (!adult_1.isSelected() && !adult_2.isSelected() && !adult_3.isSelected() && !adult_4.isSelected() &&
				!children_1.isSelected()&& !children_2.isSelected() && !children_3.isSelected() &&
				!children_4.isSelected()) {	
			cs.alertMsg("관람 인원 선택", "관람 인원이 선택되지 않았습니다", "관람 인원을 선택해주세요");	
			return;
		} else {
			if (adult_1.isSelected()) {
				System.out.println("어른 1");
			} else if (adult_2.isSelected()) {
				System.out.println("어른 2");
			} else if (adult_3.isSelected()) {
				System.out.println("어른 3");
			} else if (adult_4.isSelected()) {
				System.out.println("어른 4");
			}

			if (children_1.isSelected()) {
				System.out.println("청소년 1");
			} else if (children_2.isSelected()) {
				System.out.println("청소년 2");
			} else if (children_3.isSelected()) {
				System.out.println("청소년 3");
			} else if (children_4.isSelected()) {
				System.out.println("청소년 4");
			}

			// 티켓 확인 페이지로 이동
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

			SelectMovie_5_chkInfo.setTitle("예매 정보 확인");
			SelectMovie_5_chkInfo.show();
			
			System.out.println(sd.getSelDate());

		}
	}

	@Override
	public void previousPage4(Parent selectPerson) {
		// TODO Auto-generated method stub
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
