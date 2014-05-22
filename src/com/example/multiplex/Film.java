package com.example.multiplex;

public class Film {
	private String name;
	private String org_name;
	private String image_path;
	private int rating;
	private String description;
	private String genes;
	private int filmID;
	private String web;
	
	public int getFilmID() {
		return filmID;
	}
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	public Film(String name, String org_name, String path, int rating, String desc, String gen, int f, String w){
		genes = gen;
		this.org_name = org_name;
		this.name = name;
		this.image_path = path;
		this.rating = rating;
		this.description = desc;
		filmID =f;
		web = w;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getorg_name(){
		return org_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenes() {
		return genes;
	}
	public void setGenes(String genes) {
		this.genes = genes;
	}

}
