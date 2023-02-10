package test_1.Service;

import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import test_1.View.*;
import test_1.DAO.*;

public class TicketServiceImpl implements TicketService{
	private Ticket ticket;
	private ArrayList<Ticket> tickets;
	private TicketDAO td;
	
	public TicketServiceImpl(){
		td = new TicketDAOImpl();
	}

	@Override
	public void printTicket(Parent root) {
		// TODO Auto-generated method stub
		Label title = (Label)root.lookup("#confirmTitle");
//		tickets = td.selectTicket(ticket.getUserId());
//		confirmTitle.setText(ticket.getUserId() + "님의 예매 내역");
		title.setText("님의 예매 내역");
	}

}
