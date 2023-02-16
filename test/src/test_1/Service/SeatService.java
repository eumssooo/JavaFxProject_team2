package test_1.Service;

import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import test_1.View.selData;

public interface SeatService {
	public void previousPage(Parent seat, selData sd);
	public void NextPage(Parent root, selData sd);
	public void selectSeat(Parent root, selData sd);
	public CheckBox[] searchSeat(Parent root, selData sd);
	public int selSeatCnt(selData sd);
}

