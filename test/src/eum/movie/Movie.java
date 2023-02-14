package eum.movie;

public class Movie {
	private String title;
	private String time;
	private String ageLimit;
	private String imageSrc;
	private int runtime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
