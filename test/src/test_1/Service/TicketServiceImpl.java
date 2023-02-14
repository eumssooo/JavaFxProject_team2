package test_1.Service;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import test_1.View.*;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.DAO.*;

public class TicketServiceImpl implements TicketService{
//	private Ticket ticket;
	private ArrayList<Ticket> tickets;
	private TicketDAO td;
	private CommonService cs;
	
	public TicketServiceImpl(){
		td = new TicketDAOImpl();
		cs = new CommonServiceImpl();
		
		cs.conn();
	}

	@Override
	public void printTicket(Parent root, Ticket ticket) {
		// TODO Auto-generated method stub
		tickets = td.selectTicket(ticket.getUserId());	//ticket.getUserId()
		Label confirmTitle = (Label)root.lookup("#confirmTitle");
		confirmTitle.setText(ticket.getUserName() + " 님의 예매 내역"); //ticket.getUserName()
		String cancel = "#cancelTicket";
		Button[] cancels = new Button[10];
		
		for(int i = 0; i<tickets.size(); i++) {
			cancel += i;
			System.out.println(cancel);
			cancels[i] = (Button)root.lookup(cancel);
			cancels[i].setVisible(true);
			cancel = "#cancelTicket";
		}
		
		
		TableView<Ticket> tableView = new TableView<Ticket>();
		tableView = (TableView)root.lookup("#confirmTable");
		
		TableColumn<Ticket, String> userName = new TableColumn<>("이름");
		userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		TableColumn<Ticket, String> movieName = new TableColumn<>("제목");
		movieName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
		TableColumn<Ticket, String> roomNum = new TableColumn<>("관");
		roomNum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
		TableColumn<Ticket, String> day = new TableColumn<>("상영날짜");
		day.setCellValueFactory(new PropertyValueFactory<>("day"));
		TableColumn<Ticket, String> seatNum = new TableColumn<>("좌석");
		seatNum.setCellValueFactory(new PropertyValueFactory<>("seatNum"));
		TableColumn<Ticket, Integer> cost = new TableColumn<>("비용");
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		TableColumn<Ticket, Integer> reserveDate = new TableColumn<>("예매날짜");
		reserveDate.setCellValueFactory(new PropertyValueFactory<>("reserveDate"));
		
		tableView.getColumns().addAll(userName, movieName,roomNum,day,seatNum,cost,reserveDate);
		
		List<Ticket> ticketList = td.selectTicket(ticket.getUserId());	// ticket.getUserId()
		ObservableList<Ticket> data = 
				FXCollections.observableArrayList(ticketList);
		tableView.setItems(data);
//		
//		ObservableList<TableRowDataModel> myList = FXCollections.observableArrayList(
//	            new TableRowDataModel(new SimpleStringProperty("Jin Seong"), new SimpleStringProperty("DaeJeon"), new SimpleStringProperty("male"), new SimpleIntegerProperty(3)),
//	            new TableRowDataModel(new SimpleStringProperty("Jang Ho"), new SimpleStringProperty("SiGol"), new SimpleStringProperty("male"), new SimpleIntegerProperty(3)),
//	            new TableRowDataModel(new SimpleStringProperty("Sung Bin"), new SimpleStringProperty("SuWon"), new SimpleStringProperty("male"), new SimpleIntegerProperty(3)),
//	            new TableRowDataModel(new SimpleStringProperty("Key Hwang"), new SimpleStringProperty("DaeJeon"), new SimpleStringProperty("male"), new SimpleIntegerProperty(3)),
//	            new TableRowDataModel(new SimpleStringProperty("Seong Woo"), new SimpleStringProperty("DaeJeon"), new SimpleStringProperty("male"), new SimpleIntegerProperty(3)),
//	            new TableRowDataModel(new SimpleStringProperty("I kyun"), new SimpleStringProperty("BuSan"), new SimpleStringProperty("male"), new SimpleIntegerProperty(3))
//	    );
		
	}

}
