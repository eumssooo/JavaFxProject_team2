package eum.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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


	public boolean insertSelectedData (selData sd) {
		// TODO Auto-generated method stub		
		// 관람일, 영화, 관람회차, 관람 인원 수

		String sql = "insert into selectedData values(?,?,?,?)";


		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sd.getSelDate());
			pstmt.setString(2, sd.getSelTitle());
			pstmt.setInt(3, sd.getSelSession());
			pstmt.setInt(4, sd.getSelPersonNum());

			int result = pstmt.executeUpdate();

			if(result == 1) {
				System.out.println("데이터 저장 성공");
				pstmt.close();
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
//
//
//	@Override
//	public boolean insertSelDate(selData sd) {
//		// TODO Auto-generated method stub
//		String sql = "insert into selectedData (selDate) values(?)";
//
//
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, sd.getSelDate());
//			int result = pstmt.executeUpdate();
//
//			if(result == 1) {
//				System.out.println("데이터 저장 성공");
//				pstmt.close();
//				return true;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//
//
//	@Override
//	public boolean insertSelTitle(selData sd) {
//		// TODO Auto-generated method stub
//		String sql = "insert into selectedData (selTitle) values(?)";
//
//
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, sd.getSelTitle());
//			int result = pstmt.executeUpdate();
//
//			if(result == 1) {
//				System.out.println("데이터 저장 성공");
//				pstmt.close();
//				return true;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//
//
//	@Override
//	public boolean insertSelSession(selData sd) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//	@Override
//	public boolean insertSelPersonNum(selData sd) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//

}











