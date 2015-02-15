package me.zacharilius.JaSON;

import java.util.List;
import java.util.LinkedList;

public class Article {
	private String title;
    private String url;
    private List<String> categories;
    private List<String> tags;
    
    public Article(){
    	categories = new LinkedList<String>();
    	tags = new LinkedList<String>();
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void addCategory(String category) {
		categories.add(category);
	}

	public void addTag(String tag) {
		tags.add(tag);
	}
}
