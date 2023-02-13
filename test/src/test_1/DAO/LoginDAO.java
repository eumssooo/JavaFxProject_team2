package test_1.DAO;

import test_1.View.Customer;

public interface LoginDAO {
	public boolean insert(Customer c, boolean clickId, boolean clickAuth, boolean authFin);
	public boolean chkId(String id);
	public boolean chkLogin(String id, String pw);
	public String searchId(String name, String phone);
	public String searchPw(String id, String name, String phone);
	public boolean chkTel(String phone);
}
