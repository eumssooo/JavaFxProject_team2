package test_1.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.View.Customer;

public class LoginDAOImpl implements LoginDAO{
	
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	CommonService cs;
	
	public LoginDAOImpl() {
		// TODO Auto-generated constructor stub
		
		cs = new CommonServiceImpl();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user = "system";
			String pass = "oracle";
			
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("오라클 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("오라클 연결 실패");
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean insert(Customer c, boolean clickId, boolean clickAuth, boolean authFin) {
		// TODO Auto-generated method stub
		
		// 아이디 중복확인 완료 + 인증번호 전송 + 인증번호 확인 완료 시 db에 값 저장하기
		if(clickId && clickAuth && authFin) {
			String sql = "insert into customer values(?,?,?,?,?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, c.getId());
				pstmt.setString(2, c.getPw());
				pstmt.setString(3, c.getName());
				pstmt.setInt(4, c.getAge());
				pstmt.setString(5, c.getPhone());
				
				int result = pstmt.executeUpdate();
				
				if(result == 1) {
					cs.errorMsg("회원가입", "회원가입여부", "회원가입에 성공하셨습니다");
					return true; // db에 저장 성공했을 때만 true값 반환
				}
				
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				cs.errorMsg("회원가입", "회원가입여부", "회원가입에 실패하였습니다");
			}
		// 중복확인 완료 안되면
		} else if(clickId==false){
			cs.errorMsg("회원가입","회원가입여부", "아이디 중복확인이 필요합니다");
		// 인증번호 발송 안되면
		} else if(clickAuth==false) {
			cs.errorMsg("회원가입","회원가입여부", "인증번호 발송이 필요합니다");
		// 인증번호 확인 안되면
		} else if(authFin==false) {
			cs.errorMsg("회원가입","회원가입여부", "인증번호 확인이 필요합니다");
		}
		return false;
	}

	@Override // 회원가입창에서 아이디 중복확인 클릭 시 db에 일치하는 아이디가 있는지 확인
	public boolean chkId(String id) {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		String sql = "select decode(count(*),1,'false','true')" + " from customer where id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			result = Boolean.parseBoolean(rs.getString(1)); // 아이디가 있으면 false, 없으면 true값 반환
			
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	@Override // 메인창에서 로그인 클릭 시 db에 일치하는 아이디/비밀번호가 있는지 확인
	public boolean chkLogin(String id, String pw) {
		// TODO Auto-generated method stub
		
		String sql = "select count(*) from customer where id=? and pw=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			int result = rs.getInt(1);
			
			if(result >= 1) {
				return true; // 일치하는 값이 있으면 true값 반환
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	@Override // 아이디 찾기 클릭 시 db에 (이름+전화번호가) 일치하는 아이디가 있는지 확인(일치 시 해당 값 반환)
	public String searchId(String name, String phone) {
		// TODO Auto-generated method stub
		
		String sql = "select id from customer where name=? and phone=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			String result = rs.getString(1);
			
			if(result != null) {
				return result; // 일치하는 값이 있으면 반환
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override // 비밀번호 찾기 클릭 시 db에 (아이디+이름+전화번호가) 일치하는 비밀번호가 있는지 확인(일치 시 해당 값 반환)
	public String searchPw(String id, String name, String phone) {
		// TODO Auto-generated method stub
		
		String sql = "select id from customer where id=? and name=? and phone=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			String result = rs.getString(1);
			
			if(result != null) {
				return result; // 일치하는 값이 있으면 반환
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override // 인증번호 전송 클릭 시 db에 전화번호 중복 있는지 확인
	public boolean chkTel(String phone) {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		String sql = "select decode(count(*),1,'false','true')" + " from customer where phone=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			result = Boolean.parseBoolean(rs.getString(1)); // 전화번호가 있으면 false, 없으면 true값 반환
			
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

}
