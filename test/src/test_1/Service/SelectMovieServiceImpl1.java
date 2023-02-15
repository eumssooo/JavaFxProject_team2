package test_1.Service;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.SelectMovieController;
import test_1.DAO.MovieDAO;
import test_1.DAO.MovieDAOImpl;
import test_1.View.selData;


public class SelectMovieServiceImpl1 implements SelectMovieService1{


	private static CommonService cs;
	private static  MovieDAO md;
	Parent selectMovie;
	private selData sd;

	public SelectMovieServiceImpl1 () {
		cs = new CommonServiceImpl();
		md = new MovieDAOImpl();
		sd = new selData();
	}


	@Override
	public void nextPage1(Parent selectDate) {
		// TODO Auto-generated method stub


		ComboBox<String> cmbDate = (ComboBox<String>) selectDate.lookup("#cmbDate"); // 날짜 선택 콤보 박스
		if(cmbDate.getValue() != null) {	// 콤보박스 선택시 (데이터 변경 필요)
			if(cmbDate.getValue().equals("2월 15일")) {
				sd.setSelDate(cmbDate.getValue());
				System.out.println(sd.getSelDate());
			} else if(cmbDate.getValue().equals("2월 16일")) {
				sd.setSelDate(cmbDate.getValue());
				System.out.println(sd.getSelDate());
			} else if(cmbDate.getValue().equals("2월 17일")) {
				sd.setSelDate(cmbDate.getValue());
				System.out.println(sd.getSelDate());
			} else if(cmbDate.getValue().equals("2월 18일")) {
				sd.setSelDate(cmbDate.getValue());
				System.out.println(sd.getSelDate());
			} else if(cmbDate.getValue().equals("2월 19일")) {
				sd.setSelDate(cmbDate.getValue());
				System.out.println(sd.getSelDate());
			}

			// 영화 선택 페이지 (다음 페이지) 로드
			Stage SelectMovie_2_Movie = (Stage) selectDate.getScene().getWindow();
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
			ctrl.setSelData(sd);

			SelectMovie_2_Movie.setTitle("영화 선택");
			SelectMovie_2_Movie.show();

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
			
		}else if (cmbDate.getValue() == null)  // 콤보박스 미선택시 alert 메세지
		{
			cs.alertMsg("날짜 선택", "상영 날짜가 선택되지 않았습니다", "상영 날짜를 선택해주세요");
			cmbDate.requestFocus();
			return;		
		}
	}
}
