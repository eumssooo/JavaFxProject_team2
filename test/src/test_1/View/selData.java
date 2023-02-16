package test_1.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class selData {
	private String userName;
	private String userId;
	private String selDate;
	private String selTitle;
	private int selRoom;
	private int selAdultNum;
	private int selChildrenNum;
	private String selTime;
	private int cost;
	private String selSeatNum;
	private String reserveDate;
	
	public void print_selData() {
		System.out.println("--SELDATA 출력--");
		System.out.println(userName);
		System.out.println(userId);
		System.out.println(selDate);
		System.out.println(selTitle);
		System.out.println(selRoom);
		System.out.println(selAdultNum);
		System.out.println(selChildrenNum);
		System.out.println(selTime);
		System.out.println(cost);
		System.out.println(selSeatNum);
		System.out.println(reserveDate);
	}

	public String getSelDate() {
		return selDate;
	}
	public void setSelDate(String selDate) {
		this.selDate = selDate;
	}
	public String getSelTitle() {
		return selTitle;
	}
	public void setSelTitle(String selTitle) {
		this.selTitle = selTitle;
	}
	public int getSelAdultNum() {
		return selAdultNum;
	}
	public void setSelAdultNum(int selAdultNum) {
		this.selAdultNum = selAdultNum;
	}
	public int getSelChildrenNum() {
		return selChildrenNum;
	}
	public void setSelChildrenNum(int selChildrenNum) {
		this.selChildrenNum = selChildrenNum;
	}
	public String getSelTime() {
		return selTime;
	}
	public void setSelTime(String selTime) {
		this.selTime = selTime;
	}
	public int getSelRoom() {
		return selRoom;
	}
	public void setSelRoom(int selRoom) {
		this.selRoom = selRoom;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getSelSeatNum() {
		return selSeatNum;
	}
	public void setSelSeatNum(String selSeatNum) {
		this.selSeatNum = selSeatNum;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초");
		Calendar now = Calendar.getInstance();
		String reserveDate = f.format(now.getTime());
		
		this.reserveDate = reserveDate;
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
	public void setUserId(String userId) {
		this.userId = userId;
	}
}

