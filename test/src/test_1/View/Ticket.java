package test_1.View;

public class Ticket {
	private String userName;
	private String userId;
	private String seatNum;
	private int roomNum;
	private String movieName;
	private String day;
	private String reserveDate;
	private	int cost;
	private int person;
	
	public Ticket() {}	// 기본 이니셜라이저
	public Ticket(String userName, String userId, String seatNum,
			int roomNum, String movieName, String day, String reserveDate,
			int cost, int person) {
		this.userName = userName;
		this.userId = userId;
		this.seatNum = seatNum;
		this.roomNum = roomNum;
		this.movieName = movieName;
		this.day = day;
		this.reserveDate = reserveDate;
		this.cost = cost;
		this.person = person;
	}
	
	public void print_Ticket() {
		System.out.println("--TICKET 출력--");
		System.out.println(userName);
		System.out.println(userId);
		System.out.println(seatNum);
		System.out.println(roomNum);
		System.out.println(movieName);
		System.out.println(day);
		System.out.println(reserveDate);
		System.out.println(cost);
		System.out.println(person);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId() {
		this.userId = Customer.getId();
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	
	
}
