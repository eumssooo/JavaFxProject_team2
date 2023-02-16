package test_1.DAO;

import java.util.ArrayList;

import test_1.View.Seat;
import test_1.View.selData;

public interface SeatDAO {
	public ArrayList<Seat> selectSeat(String userId);	
	public void useSeat(Seat s);						
	public void cancelseat(Seat s);					
						
	public void nextPageSeat(selData sd);
	public String seatCheck(selData sd);
}
