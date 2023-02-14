package eum.movie;

import javafx.scene.Parent;
import eum.movie.service.SelectMovieService1;
import eum.movie.service.SelectMovieService2;
import eum.movie.service.SelectMovieService3;
import eum.movie.service.SelectMovieService4;
import eum.movie.service.SelectMovieService5;
import eum.movie.service.SelectMovieServiceImpl1;
import eum.movie.service.SelectMovieServiceImpl2;
import eum.movie.service.SelectMovieServiceImpl3;
import eum.movie.service.SelectMovieServiceImpl4;
import eum.movie.service.SelectMovieServiceImpl5;


public class SelectMovieController {

	private Parent root;
	private Parent selectMovie;
	private Parent selectSession;
	private Parent selectPerson;
	private Parent chkInfo;
	private selData sd;



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
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}

	public void nextPage1 () {
		sms1.nextPage1(root);
	}
	
	
	// 2페이지
	public void setSelectMovie(Parent selectMovie) {
		// TODO Auto-generated method stub
		this.selectMovie = selectMovie;
	}
	
	public void previousPage2 () {
		sms2.previousPage2(selectMovie);
	}

	public void nextPage2 () {
		sms2.nextPage2(selectMovie);
	}
	
	// 3페이지
	public void setSelectSession(Parent selectSession) {
		// TODO Auto-generated method stub
		this.selectSession = selectSession;
	}
	
	public void previousPage3 () {
		sms3.previousPage3(selectSession);
	}

	public void nextPage3 () {
		sms3.nextPage3(selectSession);
	}

	// 4페이지
	public void setSelectPerson(Parent selectPerson) {
		// TODO Auto-generated method stub
		this.selectPerson = selectPerson;
	}
	
	public void previousPage4 () {
		sms4.previousPage4(selectPerson);
	}

	public void nextPage4 () {
		sms4.nextPage4(selectPerson);
	}
	
	// 5페이지
	public void setCheckInfo(Parent chkInfo) {
		// TODO Auto-generated method stub
		this.chkInfo = chkInfo;
	}
	
	public void previousPage5 () {
		sms5.previousPage5(chkInfo);
	}


	public void checkMovieInfo() {
		sms5.checkMovieInfo(root);
	}












}
