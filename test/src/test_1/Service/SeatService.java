package test_1.Service;

import javafx.scene.Parent;
import test_1.View.selData;

public interface SeatService {
	//public void previousPage();
	public void printSeat(Parent root);
	public void joinSeat(Parent root);
	public boolean SeatServiceChk(String text, String text2);
	public void previousPage(Parent seat, selData sd);
	public void NextPage(Parent root, selData sd);
	public void selectSeat(Parent root, selData sd);
}

