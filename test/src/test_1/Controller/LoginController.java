package test_1.Controller;

import javafx.scene.Parent;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.Service.JoinService;
import test_1.Service.JoinServiceImpl;
import test_1.Service.LoginService;
import test_1.Service.LoginServiceImpl;
import test_1.Service.SearchService;
import test_1.Service.SearchServiceImpl;
import test_1.View.Customer;

public class LoginController {
	
	public static Customer cust;

	Parent root; // 로그인(login.fxml)
	Parent join; // 회원가입(join.fxml)
	Parent search; // 찾기

	LoginService ls;
	JoinService js;
	CommonService cs;
	SearchService ss;

	public LoginController() {
		// TODO Auto-generated constructor stub
		ls = new LoginServiceImpl();
		js = new JoinServiceImpl();
		cs = new CommonServiceImpl();
		ss = new SearchServiceImpl();
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}

	public void setJoin(Parent join) {
		// TODO Auto-generated method stub
		this.join = join;
	}

	public void setSearch(Parent search) {
		// TODO Auto-generated method stub
		this.search = search;
	}
	
	// 티켓박스의 로그인 버튼 클릭 시 실행
	public void loginProc() {
		ls.loginProc(root);
	}
	
	public void setCustomer (Customer cust) {
		this.cust= cust;
	}

	// 티켓박스의 회원가입 버튼 클릭 시 실행
	public void joinProc() {
		ls.joinProc(root);
	}

	// 회원가입의 회원가입 버튼 클릭 시 실행
	public void joinMember() {
		js.JoinMember(join);
	}

	// 회원가입의 아이디 중복확인 버튼 클릭 시 실행
	public void idDupProc() {
		js.idDupProc(join);
	}

	// 회원가입의 인증번호 발송 버튼 클릭 시 실행
	public void authSendProc() {
		js.authSendProc(join);
	}

	// 회원가입의 인증번호 확인 버튼 클릭 시 실행
	public void authOkProc() {
		js.authOkProc(join);
	}
	
	// 회원가입의 생년월일 선택 시 나이 계산해서 나이창에 입력되도록
	public void birthProc() {
		js.birthProc(join);
	}
	
	// 티켓박스의 아이디 찾기 버튼 클릭 시 실행
	public void idSearchProc() {
		ls.idSearchProc(root);
	}

	// 티켓박스의 비밀번호 찾기 버튼 클릭 시 실행
	public void pwSearchProc() {
		ls.pwSearchProc(root);
	}
	
	// 아이디 찾기의 찾기 버튼 클릭 시 실행
	public void idSearch() {
		ss.idSearch(search);
	}
	
	// 비밀번호 찾기의 찾기 버튼 클릭 시 실행
	public void pwSearch() {
		ss.pwSearch(search);
	}

}

