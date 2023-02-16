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
	public static ArrayList<Ticket> tickets = new ArrayList<Ticket>();;
	public static Button[] cancels = new Button[20];
	private TicketDAO td;
	private CommonService cs;
	private selData sd;

	public TicketServiceImpl(){
		td = new TicketDAOImpl();
		cs = new CommonServiceImpl();
		sd = new selData();
		cs.conn();
	}

	@Override
	public void printTicket(Parent root, selData sd) {
		// TODO Auto-generated method stub
		Label confirmTitle = (Label)root.lookup("#confirmTitle");
		confirmTitle.setText("???" + " 님의 예매 내역"); //ticket.getUserName()
		String cancel = "#cancelTicket";
		
		for(int i = 0; i<tickets.size(); i++) {
			cancel += i;
			System.out.println(cancel);
			cancels[i] = (Button)root.lookup(cancel);
			cancels[i].setVisible(true);
			cancel = "#cancelTicket";
		}

		TableView tableView = (TableView)root.lookup("#confirmTable");
		if(tableView.getColumns().isEmpty()) {
			TableColumn<Ticket, String> userName = new TableColumn<>("userName");
			userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
			TableColumn<Ticket, String> movieName = new TableColumn<>("movieName");
			movieName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
			TableColumn<Ticket, Integer> roomNum = new TableColumn<>("roomNum");
			roomNum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
			TableColumn<Ticket, String> day = new TableColumn<>("day");
			day.setCellValueFactory(new PropertyValueFactory<>("day"));
			TableColumn<Ticket, String> seatNum = new TableColumn<>("seatNum");
			seatNum.setCellValueFactory(new PropertyValueFactory<>("seatNum"));
			TableColumn<Ticket, Integer> cost = new TableColumn<>("cost");
			cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
			TableColumn<Ticket, Integer> reserveDate = new TableColumn<>("reserveDate");
			reserveDate.setCellValueFactory(new PropertyValueFactory<>("reserveDate"));

			tableView.getColumns().addAll(userName,movieName,roomNum,day,seatNum,cost,reserveDate);

			//List<Ticket> ticketList = td.selectTicket(ticket.getUserId());	// ticket.getUserId()
			ObservableList<Ticket> data = 
					FXCollections.observableArrayList(tickets);
			tableView.setItems(data);
		}
		
	}

	@Override
	public void insertTicketFromSd(selData sd) {
		// TODO Auto-generated method stub

		Ticket t = new Ticket(sd.getUserName(), sd.getUserId(), sd.getSelSeatNum(),
				sd.getSelRoom(), sd.getSelTitle(), sd.getSelDate(), sd.getReserveDate(),
				sd.getCost(), sd.getSelAdultNum() + sd.getSelChildrenNum());
		// sd.getUserName(), sd.getUserId()
		t.print_Ticket();
		tickets.add(t);
	}

	@Override
	public void cancelTickets() {
		// TODO Auto-generated method stub
		for(int i = 0; i<tickets.size(); i++) {
			if(cancels[i].isPressed()) {
				tickets.remove(i);
			}
		}
	}



}
