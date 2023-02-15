package test_1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.View.selData;


public class MovieDAOImpl implements MovieDAO {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static CommonService cs;


	selData sd = new selData();

	public MovieDAOImpl() {
		// TODO Auto-generated constructor stub
		cs = new CommonServiceImpl();
		con = cs.conn();
	}

	
	public int movieRunningTime(String title) { // 러닝타임 받아오기
		// TODO Auto-generated method stub
		String sql = "select runtime from movie where title = ?";
		// select runtime from movie where title = '타이타닉';
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,title);
				
			rs = pstmt.executeQuery();

			while (rs.next()) {
			int runtime = rs.getInt(1);			
			return runtime;
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
		// 회원 나이, 상영 등급 받아오기 추가 필요
	}
}











