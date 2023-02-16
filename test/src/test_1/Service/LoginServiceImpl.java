package test_1.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import test_1.Controller.SelectMovieController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import test_1.View.Customer;
import test_1.View.selData;

public class LoginServiceImpl implements LoginService{

	CommonService cs;
	LoginDAO ds;
	private selData sd;
	private Customer cust;

	
	public LoginServiceImpl() {
		// TODO Auto-generated constructor stub
		cs = new CommonServiceImpl();
		ds = new LoginDAOImpl();
		cust = new Customer();
		sd = new selData();
	}

	
	@Override // 티켓박스의 로그인 버튼 클릭 시 실행
	public void loginProc(Parent root, selData sd) {
		// TODO Auto-generated method stub
		
		TextField id = (TextField) root.lookup("#loginId");
		PasswordField pw = (PasswordField) root.lookup("#loginPw");

		if(ds.chkLogin(id.getText(), pw.getText())) {
			cs.errorMsg("로그인", "로그인 결과", "로그인에 성공하였습니다");
			cust.setId(id.getText());
			
			// 로그인 창 닫기 + 상영 날짜 선택 이동
			Stage s = (Stage) root.getScene().getWindow();
			
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../SelectMovie_1_Date.fxml"));
			
			Parent selectDate = null;
			try {
				selectDate = loader.load();
				s.setScene(new Scene(selectDate));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SelectMovieController ctrl = loader.getController();
			ctrl.setSelectDate(selectDate);
			ctrl.setSelData(sd);
			
			s.setTitle("상영 날짜 선택");
			s.show();
			
			Label loginName = (Label) selectDate.lookup("#loginName");
			loginName.setText(cust.getId() + " 님");
			// 상영날짜 선택 콤보박스 내용 입력 (로그인 이후에 넣기)
			ComboBox<String> cmbDate = (ComboBox<String>) selectDate.lookup("#cmbDate");
			// 현재 날짜부터 5일 선택 가능
			Calendar cal = new GregorianCalendar();	
			SimpleDateFormat formatter = new SimpleDateFormat("MM월 dd일");
			Date date = new Date();
			date = cal.getTime();
			System.out.println(formatter.format(date));
			cmbDate.getItems().add(formatter.format(date));
			for (int i= 0;i<4;i++) {
			cal.add(Calendar.DATE,1);
			date = cal.getTime();
			System.out.println(formatter.format(date));
			cmbDate.getItems().add(formatter.format(date));
			}
			
			
			
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

