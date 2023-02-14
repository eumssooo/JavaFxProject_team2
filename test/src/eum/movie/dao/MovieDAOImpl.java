package eum.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import eum.movie.service.CommonService;
import eum.movie.service.CommonServiceImpl;
import eum.movie.Movie;
import eum.movie.selData;

public class MovieDAOImpl implements MovieDAO {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static CommonService cs;

	Movie m = new Movie();
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
	}
}











