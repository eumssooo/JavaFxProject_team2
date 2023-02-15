package test_1.Service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.SeatController;
import test_1.Controller.SelectMovieController;
import test_1.DAO.MovieDAO;
import test_1.DAO.MovieDAOImpl;
import test_1.View.Customer;
import test_1.View.selData;

public class SelectMovieServiceImpl5 implements SelectMovieService5{

	Parent selectPerson;
	Parent root;

	selData sd = new selData();
	private Customer cust;

	private static CommonService cs;
	private static  MovieDAO md;

	public SelectMovieServiceImpl5 () {
		cs = new CommonServiceImpl();
		md = new MovieDAOImpl();
	}


	@Override
	public void checkMovieInfo(Parent chkInfo,selData sd) { 
		// 좌석선택 화면으로 넘기기
		// TODO Auto-generated method stub
		Stage s = (Stage) chkInfo.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../Seat.fxml"));
		
		try {
			root = loader.load();

			s.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}

		SeatController ctrl = loader.getController();
		ctrl.setSeat(root);
		ctrl.setSelData(sd);

		s.setTitle("좌석 선택");
		s.show();
				
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
		
		// 로그인 된 아이디 표시
		Label loginName = (Label) selectPerson.lookup("#loginName");
		loginName.setText(cust.getId() + " 님");
	}

}
