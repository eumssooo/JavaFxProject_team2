package test_1.DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import test_1.Common.*;
import test_1.View.*;


public class TicketDAOImpl implements TicketDAO{
	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	private static CommonService cs;
	

	public TicketDAOImpl() {
		// TODO Auto-generated constructor stub
		cs = new CommonServiceImpl();
		con = cs.conn();
	}

	@Override
	public ArrayList<Ticket> selectTicket(String userId) {
		String sql = "select * from ticket where userId = ? ";

		ArrayList<Ticket> ticketList = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Ticket t = new Ticket();
				t.setUserName(rs.getString(1));
				t.setSeatNum(rs.getString(3));
				t.setRoomNum(rs.getInt(4));
				t.setMovieName(rs.getString(5));
				t.setDay(rs.getString(6));
				t.setTime(rs.getString(7));
				t.setReserveDate(rs.getString(8));
				t.setCost(rs.getInt(9));
				t.setPerson( rs.getInt(10));
				
				ticketList.add(t);
				System.out.println("티켓 확인 작업");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cs.alertMsg("티켓 불러오기 실패", "티켓 불러오기 실패", "티켓 불러오기를 실패하셨습니다.");
		}

		return ticketList;
	}
	
	@Override
	public void cancelTicket(Ticket t) {
		String sql = "delete from ticket "+ "where userId = ? and seatNum = ? and roomNum = ? and movieName = ? and day = ? and time=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t.getUserId());
			pstmt.setString(2, t.getSeatNum());
			pstmt.setString(3, String.valueOf(t.getRoomNum()));
			pstmt.setString(4, t.getMovieName());
			pstmt.setString(5, t.getDay());;
			pstmt.setString(6, t.getTime());
			pstmt.executeUpdate();
			System.out.println("티켓 예약 취소 작업");
			
			cs.alertMsg("티켓 예약 취소 성공", "티켓 예약 취소 성공", "티켓 예약 취소에 성공하셨습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			cs.alertMsg("티켓 예약 취소 실패", "티켓 예약 취소 실패", "티켓 예약 취소에 실패하셨습니다.");
		}
	}

	@Override
	public void cancelSeat(selData sd) {
		String sql = "delete from seat where roomNum = ? and seatNum = ? and day = ? and reserved = ? ";

		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(sd.getSelRoom()));
			pstmt.setString(2, sd.getSelSeatNum());
			pstmt.setString(3, sd.getSelDate());
			pstmt.setString(4, sd.getSelTime());
			
			pstmt.executeUpdate();
			System.out.println("좌석 예약 취소 작업");

			cs.alertMsg("좌석 예약 취소 성공", "좌석 예약 취소 성공", "좌석 예약 취소에 성공하셨습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			cs.alertMsg("좌석 예약 취소 실패", "좌석 예약 취소 실패", "좌석 예약 취소에 실패하셨습니다.");
		}
	}

	@Override
	public void enrollTicket(selData sd) {
		sd.setReserveDate();	// 현재시간 setter에서 "등록하는 순간" 계산 -> 결제완료와 동시에 enrollTicket 실행

		String sql = "insert into ticket values (?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sd.getUserName());
			pstmt.setString(2, sd.getUserId());
			pstmt.setString(3, sd.getSelSeatNum());
			pstmt.setInt(4, sd.getSelRoom());
			pstmt.setString(5, sd.getSelTitle());
			pstmt.setString(6, sd.getSelDate());
			pstmt.setString(7, sd.getSelTime());
			pstmt.setString(8, sd.getReserveDate());
			pstmt.setInt(9, sd.getCost());
			pstmt.setInt(10, sd.getSelAdultNum() + sd.getSelChildrenNum());
			System.out.println(sd.toString());
			pstmt.executeUpdate();
			System.out.println("티켓 등록 작업");
			cs.alertMsg("티켓 등록 성공", "티켓 등록 성공", "티켓 등록 성공하셨습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			cs.alertMsg("티켓 등록 실패", "티켓 등록 실패", "티켓 등록 실패하셨습니다.");
		}
	}

	@Override
	public String getDAO(String id) {
		// TODO Auto-generated method stub
		String sql = "select name from customer where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
				
			rs = pstmt.executeQuery();

			while (rs.next()) {
			String name = rs.getString(1);			
			return name;
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


}
