package test_1.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import test_1.Service.SelectMovieService1;
import test_1.Service.SelectMovieService2;
import test_1.Service.SelectMovieService3;
import test_1.Service.SelectMovieService4;
import test_1.Service.SelectMovieService5;
import test_1.Service.SelectMovieServiceImpl1;
import test_1.Service.SelectMovieServiceImpl2;
import test_1.Service.SelectMovieServiceImpl3;
import test_1.Service.SelectMovieServiceImpl4;
import test_1.Service.SelectMovieServiceImpl5;
import test_1.View.selData;


public class SelectMovieController {

	private Parent selectDate;
	private Parent selectMovie;
	private Parent selectSession;
	private Parent selectPerson;
	private Parent chkInfo;
	public static selData sd;



	private SelectMovieService1 sms1;
	private SelectMovieService2 sms2;
	private SelectMovieService3 sms3;
	private SelectMovieService4 sms4;
	private SelectMovieService5 sms5;
	public SelectMovieController () {
		sms1 = new SelectMovieServiceImpl1();
		sms2 = new SelectMovieServiceImpl2();
		sms3 = new SelectMovieServiceImpl3();
		sms4 = new SelectMovieServiceImpl4();
		sms5 = new SelectMovieServiceImpl5();
	}


	public void setSelData (selData sd) {
		this.sd= sd;
	}

	// 1페이지
	public void setSelectDate(Parent selectDate) {
		// TODO Auto-generated method stub
		this.selectDate = selectDate;
	}

	public void nextPage1 () {
		sms1.nextPage1(selectDate);
	}


	// 2페이지
	public void setSelectMovie(Parent selectMovie) {
		// TODO Auto-generated method stub
		this.selectMovie = selectMovie;
	}

	public void previousPage2 () {
		sms2.previousPage2(selectMovie,sd);
	}

	public void nextPage2 () {
		sms2.nextPage2(selectMovie, sd);
	}

	// 3페이지
	public void setSelectSession(Parent selectSession) {
		// TODO Auto-generated method stub
		this.selectSession = selectSession;
	}

	public void previousPage3 () {
		sms3.previousPage3(selectSession, sd);
	}

	public void nextPage3 () {
		sms3.nextPage3(selectSession,sd);
	}

	// 4페이지
	public void setSelectPerson(Parent selectPerson) {
		// TODO Auto-generated method stub
		this.selectPerson = selectPerson;
	}

	public void previousPage4 () {
		sms4.previousPage4(selectPerson, sd);
	}

	public void nextPage4 () {
		sms4.nextPage4(selectPerson,sd);
	}

	// 5페이지
	public void setCheckInfo(Parent chkInfo) {
		// TODO Auto-generated method stub
		this.chkInfo = chkInfo;
	}

	public void previousPage5 () {
		sms5.previousPage5(chkInfo, sd);
	}


	public void checkMovieInfo() {
		sms5.checkMovieInfo(chkInfo,sd);
	}

	public void colorAction3(ActionEvent event) {
		sms3.colorChange(selectSession);
	}
	
	public void colorAction4(ActionEvent event) {
		sms4.colorChange(selectPerson);
	}

}

