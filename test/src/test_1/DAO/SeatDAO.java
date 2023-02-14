package project;

import java.util.ArrayList;

public interface SeatDAO {
	public ArrayList<Seat> selectSeat(String userId);	
	public void useSeat(Seat s);						
	public void cancelseat(Seat s);					
						
							
}
