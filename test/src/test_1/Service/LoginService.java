package test_1.Service;

import javafx.scene.Parent;
import test_1.View.Customer;
import test_1.View.selData;

public interface LoginService {
	public void loginProc(Parent root, selData sd);
	public Parent joinProc(Parent root);
	public Parent idSearchProc(Parent root);
	public Parent pwSearchProc(Parent root);
}
