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
		
		Label confirmTitle = (Label)root.lookup("#confirmTitle");
		tickets = td.selectTicket(ticket.getUserId());	//ticket.getUserId()
		confirmTitle.setText(ticket.getUserName() + " 님의 예매 내역"); //ticket.getUserName()
		
		Button cancelTicket = (Button)root.lookup("#cancelTicket");
		
		TableView<Ticket> tableView = new TableView<Ticket>();
		tableView = (TableView)root.lookup("#confirmTable");
		
		TableColumn<Ticket, String> userName = new TableColumn<>("userName");
		userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		TableColumn<Ticket, String> movieName = new TableColumn<>("movieName");
		movieName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
		TableColumn<Ticket, String> roomNum = new TableColumn<>("roomNum");
		roomNum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
		TableColumn<Ticket, String> day = new TableColumn<>("day");
		day.setCellValueFactory(new PropertyValueFactory<>("day"));
		TableColumn<Ticket, String> seatNum = new TableColumn<>("seatNum");
		seatNum.setCellValueFactory(new PropertyValueFactory<>("seatNum"));
		TableColumn<Ticket, Integer> cost = new TableColumn<>("cost");
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		TableColumn<Ticket, Integer> reserveDate = new TableColumn<>("reserveDate");
		reserveDate.setCellValueFactory(new PropertyValueFactory<>("reserveDate"));
		
		tableView.getColumns().addAll(userName, movieName,roomNum,day,seatNum,cost,reserveDate);
		
		List<Ticket> ticketList = td.selectTicket(ticket.getUserId());	// ticket.getUserId()
		ObservableList<Ticket> data = 
				FXCollections.observableArrayList(ticketList);
		tableView.setItems(data);
		
	}

}
