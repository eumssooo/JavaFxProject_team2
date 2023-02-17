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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import test_1.View.*;
import test_1.Common.CommonService;
import test_1.Common.CommonServiceImpl;
import test_1.DAO.*;

public class TicketServiceImpl implements TicketService{
	//	private Ticket ticket;
	public static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	public static TableView<Ticket> tableView = new TableView<>();
	private TicketDAO td;
	private CommonService cs;
	private selData sd;
	Button cancelbtn = new Button();
	Ticket t = new Ticket();
	List<Ticket> ticketList;

	public TicketServiceImpl(){
		td = new TicketDAOImpl();
		cs = new CommonServiceImpl();
		sd = new selData();
		cs.conn();
	}

	@Override
	public void printTicket(Parent root, selData sd) {
		// TODO Auto-generated method stub
//		sd.setUserName(td.getDAO(Customer.getId()));
		
		Label confirmTitle = (Label)root.lookup("#confirmTitle");
		confirmTitle.setText(td.getDAO(Customer.getId()) + " 님의 예매 내역"); //ticket.getUserName()
		cancelbtn = (Button)root.lookup("#cancelTicket");
		cancelbtn.setVisible(true);
		
		tableView = (TableView)root.lookup("#confirmTable");
		if(tableView.getColumns().isEmpty()) {
			TableColumn<Ticket, String> userName = new TableColumn<>("이름");
			userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
			TableColumn<Ticket, String> movieName = new TableColumn<>("제목");
			movieName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
			TableColumn<Ticket, Integer> roomNum = new TableColumn<>("관");
			roomNum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
			TableColumn<Ticket, String> day = new TableColumn<>("상영일자");
			day.setCellValueFactory(new PropertyValueFactory<>("day"));
			TableColumn<Ticket, String> seatNum = new TableColumn<>("좌석");
			seatNum.setCellValueFactory(new PropertyValueFactory<>("seatNum"));
			TableColumn<Ticket, Integer> cost = new TableColumn<>("금액");
			cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
			TableColumn<Ticket, Integer> reserveDate = new TableColumn<>("예약일자");
			reserveDate.setCellValueFactory(new PropertyValueFactory<>("reserveDate"));

			tableView.getColumns().addAll(userName,movieName,roomNum,day,seatNum,cost,reserveDate);

			ticketList = td.selectTicket(Customer.getId());	// ticket.getUserId()
			
			ObservableList<Ticket> data = 
					FXCollections.observableArrayList(ticketList);
			tableView.setItems(data);
			tableView.setFixedCellSize(60);
		}
		
	}

	@Override
	public void insertTicketFromSd(selData sd) {
		// TODO Auto-generated method stub

		t = new Ticket(sd.getUserName(), sd.getUserId(), sd.getSelSeatNum(),
				sd.getSelRoom(), sd.getSelTitle(), sd.getSelDate(), sd.getSelTime(), sd.getReserveDate(),
				sd.getCost(), sd.getSelAdultNum() + sd.getSelChildrenNum());
		// sd.getUserName(), sd.getUserId()
		t.print_Ticket();
		
		tickets.addAll(td.selectTicket(sd.getUserId()));
		
	}

	@Override
	public Ticket cancelTickets() {
		// TODO Auto-generated method stub
		
		int n = tableView.getSelectionModel().getSelectedIndex();
		
	    return tickets.get(n);
	}



}
