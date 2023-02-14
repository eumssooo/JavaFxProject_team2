package eum.movie.service;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{

	@Override
	public void windowClose(ActionEvent event) {
		// TODO Auto-generated method stub
		Parent p = (Parent) event.getSource();
		Stage s = (Stage) p.getScene().getWindow();

		s.close();
	}

	@Override
	public void alertMsg(String subject, String head, String content) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(subject);
		alert.setHeaderText(head);
		alert.setContentText(content);
		alert.showAndWait();
	}

	@Override
	public Connection conn() {
		// TODO Auto-generated method stub
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "system";
		String pass = "oracle";

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(url, user, pass);
			System.out.println("오라클 연결 성공");
		} catch(Exception e) {
			System.out.println("오라클 연결 실패");
			e.printStackTrace();
		}
		return con;
	}
}
