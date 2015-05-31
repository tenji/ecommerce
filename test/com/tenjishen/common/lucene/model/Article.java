package com.tenjishen.common.lucene.model;

public class Article {

	private Integer id; // ID
	private String title; // Title
	private String content; // Content

	// Constructors
	
	public Article() {
	}
	
	public Article(Integer id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
