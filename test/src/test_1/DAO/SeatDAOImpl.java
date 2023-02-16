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
	public ArrayList<Seat> selectSeat(String userId) {
		// TODO Auto-generated method stub
		String sql = "select * from seat where userId = ? ";
		
		ArrayList<Seat>seatList = new ArrayList<>();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String userName = rs.getString(1);
				String seatNum = rs.getString(3);
				String roomNum = rs.getString(4);
				String movieName = rs.getString(5);
				String day = rs.getString(6);
				String reserveDate = rs.getString(7);
				int cost = rs.getInt(8);
				int person = rs.getInt(9);
				
				
				System.out.println("자석 확인 작업");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cs.alertMsg("자석 불러오기 실패", "자석 불러오기 실패", "자석 불러오기를 실패하셨습니다.");
		}

		return seatList;
	}
			

	@Override
	public void useSeat(Seat s) {
		// TODO Auto-generated method stub
		String sql = "delete from ticket "+ "where userId = ? "
				+ "and seatNum = ? and roomNum = ? and movieName = ? "
				+ "and day = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, s.getSeatNum());
			pstmt.setString(2, s.getRoomNum());
			pstmt.setString(3, s.getDay());;
			pstmt.executeUpdate();
			System.out.println("자석 예약 작업");
			
			cs.alertMsg("자석 예약 성공", "자석 예약 성공", "자석 예약에 성공하셨습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			cs.alertMsg("자석 예약 성공", "자석 예약 성공", "자석 예약에 성공하셨습니다.");
	}
	}

	@Override
	public void cancelseat(Seat s) {
		// TODO Auto-generated method stub
		String sql = "delete from seat where roomNum = ? "
				+ "and day = ? and seatNum = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, s.getRoomNum());
			pstmt.setString(2, s.getDay());
			pstmt.setString(3, s.getSeatNum());
			pstmt.executeUpdate();
			System.out.println("좌석 예약 취소 작업");

			cs.alertMsg("좌석 예약 취소 성공", "좌석 예약 취소 성공", "좌석 예약 취소에 성공하셨습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			cs.alertMsg("좌석 예약 취소 실패", "좌석 예약 취소 실패", "좌석 예약 취소에 실패하셨습니다.");
	}

	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public void nextPageSeat(selData sd) {
		// TODO Auto-generated method stub
		String sql = "insert into seat values(?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, String.valueOf(sd.getSelRoom()));
			pstmt.setString(2, sd.getSelSeatNum()); // 좌석 따로 나눠서 저장해야하남?
			pstmt.setString(3, sd.getSelDate());
			pstmt.setString(4, sd.getSelTitle());
			
			
			
//		    roomNum     varchar(30) not null, // 상영관
//		    seatNum     varchar(30) not null, // 좌석
//		    day         varchar(30) not null, // 날짜 
//		    reserved    varchar(30) not null, // 영화제목인가???? 시간인가???
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("예매 좌석 저장");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
