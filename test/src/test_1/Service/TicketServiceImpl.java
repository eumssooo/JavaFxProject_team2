package test_1.Service;

import java.util.ArrayList;
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
	public void printTicket() {
		// TODO Auto-generated method stub
		tickets = td.selectTicket(ticket.getUserId());
	}

}
