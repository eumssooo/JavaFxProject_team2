package test_1.Common;

import java.sql.Connection;

import javafx.event.ActionEvent;

public interface CommonService {
	public void windowClose(ActionEvent event);
	public void alertMsg(String subject, String head, String content);
	public Connection conn();
}
