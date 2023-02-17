package test_1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.View.Seat;
import test_1.View.selData;

public class SeatDAOImpl implements SeatDAO {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	selData sd;
	
	private static CommonService cs;
	
	
	
	public SeatDAOImpl() {
		// TODO Auto-generated constructor stub
		cs = new CommonServiceImpl();
		con =cs.conn();
		
	}
	
	@Override
	public void nextPageSeat(selData sd) {
		// TODO Auto-generated method stub
		String sql = "insert into seat values(?,?,?,?)";
		
//		String[] seat = sd.getSelSeatNum().split("/");
		
			try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, String.valueOf(sd.getSelRoom()));
				pstmt.setString(2, sd.getSelSeatNum()); // 좌석 따로 나눠서 저장해야하남?
				pstmt.setString(3, sd.getSelDate());
				pstmt.setString(4, sd.getSelTime());
				

				int result = pstmt.executeUpdate();
		
			if(result == 1) {
				System.out.println("예매 좌석 저장");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public String seatCheck(selData sd) {
		// TODO Auto-generated method stub
		String sql = "select seatNum from seat where roomNum =? and day=? and reserved=?";
		String result= "";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, String.valueOf(sd.getSelRoom()));
			pstmt.setString(2, sd.getSelDate());
			pstmt.setString(3, sd.getSelTime());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result += rs.getString("seatNum")+"/";  // 모든 행 다 불러와야 함
//				System.out.println("DAO: "+result);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}


}
