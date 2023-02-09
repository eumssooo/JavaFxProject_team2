package test_1;

import java.util.ArrayList;

public interface TicketDAO {
	public ArrayList<Ticket> selectTicket(String userId);	// 티켓 불러오기
	public void cancelTicket(Ticket t);						// 티켓 취소
	public void cancelSeat(Ticket t);						// 좌석 취소
	public void enrollTicket(Ticket t);						// 티켓 등록
}
