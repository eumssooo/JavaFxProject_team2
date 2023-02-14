package test_1.Service;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Controller.LoginController;
import test_1.Controller.SelectMovieController;
import test_1.DAO.LoginDAO;
import test_1.DAO.LoginDAOImpl;

public class LoginServiceImpl implements LoginService{

	CommonService cs;
	LoginDAO ds;

	
	public LoginServiceImpl() {
		// TODO Auto-generated constructor stub
		cs = new CommonServiceImpl();
		ds = new LoginDAOImpl();
	}

	
	@Override // 티켓박스의 로그인 버튼 클릭 시 실행
	public void loginProc(Parent root) {
		// TODO Auto-generated method stub
		
		TextField id = (TextField) root.lookup("#loginId");
		PasswordField pw = (PasswordField) root.lookup("#loginPw");

		if(ds.chkLogin(id.getText(), pw.getText())) {
			cs.errorMsg("로그인", "로그인 결과", "로그인에 성공하였습니다");
			
			// 로그인 창 닫기
//			Stage s = (Stage) root.getScene().getWindow();
//			s.close();

			//
			//
			//
			//
			//
			//
			// 로그인 성공 시 영화 예매 페이지로 넘어가도록 추후 작업 필요
			
			Stage select = (Stage) root.getScene().getWindow();
			
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../SelectMovie_1_Date.fxml")); //경로 수정
			
			Parent selectDate = null;
			try {
				selectDate = loader.load();
				select.setScene(new Scene(selectDate));
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SelectMovieController ctrl = loader.getController();
			ctrl.setSelectDate(selectDate);
			
			select.setTitle("상영 날짜 선택");
			select.show();
			
			// 상영날짜 선택 콤보박스 내용 입력 (로그인 이후에 넣기)
			ComboBox<String> cmbDate = (ComboBox<String>) selectDate.lookup("#cmbDate");
			cmbDate.getItems().addAll("2월 15일","2월 16일","2월 17일","2월 18일","2월 19일");
			
		} else {
			cs.errorMsg("로그인", "로그인 결과", "아이디/패스워드가 일치하지 않습니다");
			id.clear();
			pw.clear();
			id.requestFocus();
		}
	}


	@Override // 티켓박스의 회원가입 버튼 클릭 시 실행
	public Parent joinProc(Parent root) {
		// TODO Auto-generated method stub
		
		// 회원 가입 창이라는 새로운 창 띄움(join.fxml)
		Stage joinForm = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../join.fxml"));

		Parent join = null;
		try {
			join = loader.load();

			joinForm.setScene(new Scene(join));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LoginController ctrl = loader.getController();
		ctrl.setJoin(join);

		joinForm.setTitle("회원 가입 창");
		joinForm.show();
		
		// 생년월일 prompt text 폰트 크기 맞춰줌
		DatePicker birth = (DatePicker) join.lookup("#joinBirth");
		birth.getEditor().setFont(new Font(14));

		return join;
	}


	@Override // 티켓박스의 아이디 찾기 버튼 클릭 시 실행
	public Parent idSearchProc(Parent root) {
		// TODO Auto-generated method stub
		
		// 아이디 찾기 창이라는 새로운 창 띄움(idSearch.fxml)
		Stage idForm = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../idSearch.fxml"));
		
		Parent search = null;
		try {
			search = loader.load();
			idForm.setScene(new Scene(search));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LoginController ctrl = loader.getController();
		ctrl.setSearch(search);
		
		idForm.setTitle("아이디 찾기 창");
		idForm.show(); 
		
		return search;
		
	}


	@Override // 티켓박스의 비밀번호 찾기 버튼 클릭 시 실행
	public Parent pwSearchProc(Parent root) {
		// TODO Auto-generated method stub
		
		// 비밀번호 찾기 창이라는 새로운 창 띄움(pwSearch.fxml)
		Stage pwForm = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../pwSearch.fxml"));
		
		Parent search = null;
		try {
			search = loader.load();
			pwForm.setScene(new Scene(search));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LoginController ctrl = loader.getController();
		ctrl.setSearch(search);
		
		pwForm.setTitle("비밀번호 찾기 창");
		pwForm.show(); 
		
		return search;
	}

}

