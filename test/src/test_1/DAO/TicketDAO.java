package test_1.DAO;

import java.util.ArrayList;

import test_1.View.Ticket;
import test_1.View.selData;

public interface TicketDAO {
	public ArrayList<Ticket> selectTicket(String userId);	// 티켓 불러오기
	public void cancelTicket(Ticket t);						// 티켓 취소
	public void cancelSeat(Ticket t);						// 좌석 취소
	public void enrollTicket(selData sd);						// 티켓 등록
	public String getDAO(String id); // DAO에서 정보 가져오기(name)
}
