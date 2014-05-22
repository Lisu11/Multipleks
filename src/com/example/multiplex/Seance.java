package com.example.multiplex;

public class Seance {
	private int price;
	private int hour;
	private int minute;
	private int seanceId;
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	private int data_month;
	private int data_day;
	private String title;
	private String D;//2D lub 3D
	
	public Seance(int cena,int hour, int minute, int data_mies, int data_dzien, String tytul, String wymiar, int id){
		D = wymiar;
		title = tytul;
		data_day = data_dzien;
		data_month= data_mies;
		price = cena;
		this.hour = hour;
		this.minute = minute;
		seanceId =id;
	}
	
	public int getSeanceId() {
		return seanceId;
	}

	public void setSeanceId(int seanceId) {
		this.seanceId = seanceId;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getData_month() {
		return data_month;
	}
	public void setData_month(int data_month) {
		this.data_month = data_month;
	}
	public int getData_day() {
		return data_day;
	}
	public void setData_day(int data_day) {
		this.data_day = data_day;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
}
