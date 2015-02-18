package me.zacharilius.JaSON;

import java.util.LinkedList;

public class Jason {

	private String fName;
	private String lName;
	private String url;
	private LinkedList<String> movies;
	private LinkedList<String> tvshows;
	
	public Jason(){
		movies = new LinkedList<String>();
		tvshows = new LinkedList<String>();
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public void addTVShow(String tvshow){
		tvshows.add(tvshow);
	}
	public void addMovie(String movie){
		movies.add(movie);
	}
	public LinkedList<String> getTVShows(){
		return tvshows;
	}
	public LinkedList<String> getMovies(){
		return movies;
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("\nFirst Name: " + fName);
		s.append("\nLast Name: " + lName);
		s.append("\nURL: " + url);
		s.append("\nMovies: " + movies.toString());
		s.append("\nTV Shows: " + tvshows.toString());

		
		return s.toString();
	}
}
