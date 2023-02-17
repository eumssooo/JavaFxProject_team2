package test_1.Service;

import javafx.scene.Parent;
import test_1.View.Ticket;
import test_1.View.selData;

public interface TicketService{
	// 티켓 목록 출력
	public void printTicket(Parent root, selData sd);
	public void insertTicketFromSd(selData sd);
	public Ticket cancelTickets();
}
