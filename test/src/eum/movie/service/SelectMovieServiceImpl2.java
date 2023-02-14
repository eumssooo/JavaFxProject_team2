package eum.movie.service;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import eum.movie.Movie;
import eum.movie.SelectMovieController;
import eum.movie.selData;
import eum.movie.dao.MovieDAO;
import eum.movie.dao.MovieDAOImpl;

public class SelectMovieServiceImpl2 implements SelectMovieService2{

	private static CommonService cs;
	private static  MovieDAO md;
	Parent root;
	Parent selectSession;
	Movie m = new Movie();
	selData sd = new selData();

	public SelectMovieServiceImpl2 () {
		cs = new CommonServiceImpl();
		md = new MovieDAOImpl();
	}

	@Override
	public void nextPage2(Parent selectMovie) {
		// TODO Auto-generated method stub

		// 콤보박스에서 선택된 값 가져오기
		ComboBox<String> cmbMovie = (ComboBox<String>) selectMovie.lookup("#cmbMovie");
		

		if(cmbMovie.getValue() != null) {// null이 아니면 다음 페이지 접속 가능	
			if(cmbMovie.getValue().equals("타이타닉")) {
				sd.setSelTitle(cmbMovie.getValue());
				System.out.println(sd.getSelTitle());
			} else if(cmbMovie.getValue().equals("더 퍼스트 슬램덩크")) {
				sd.setSelTitle(cmbMovie.getValue());
				System.out.println(sd.getSelTitle());
			} else if(cmbMovie.getValue().equals("아바타 물의 길")) {
				sd.setSelTitle(cmbMovie.getValue());
				System.out.println(sd.getSelTitle());
			} else if(cmbMovie.getValue().equals("바빌론")) {
				sd.setSelTitle(cmbMovie.getValue());
				System.out.println(sd.getSelTitle());
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
		} else if (cmbMovie.getValue() == null) // null값일시 에러 메세지
		{
			cs.alertMsg("영화 선택", "영화가 선택되지 않았습니다", "영화를 선택해주세요");
			cmbMovie.requestFocus();
			return;
		}
	}

	@Override
	public void previousPage2(Parent selectMovie) {
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

		SelectMovie_1_Date.setTitle("영화 선택");
		SelectMovie_1_Date.show();
	}

}
