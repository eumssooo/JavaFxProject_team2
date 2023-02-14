package test_1.View;

public class Movie {
	private String title; // 영화 제목
	private String ageLimit; // 상영 제한 나이
	private String imageSrc; // 포스터
	private int runtime; // 영화 상영 시간
	private String time;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAgeLimit() {
		return ageLimit;
	}
	public void setAgeLimit(String agelimit) {
		this.ageLimit = agelimit;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imagesrc) {
		this.imageSrc = imagesrc;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
}
