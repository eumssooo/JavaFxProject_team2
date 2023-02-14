package test_1.DAO;

import java.util.ArrayList;

import test_1.View.Seat;

public interface SeatDAO {
	public ArrayList<Seat> selectSeat(String userId);	
	public void useSeat(Seat s);						
	public void cancelseat(Seat s);					
						
							
}
