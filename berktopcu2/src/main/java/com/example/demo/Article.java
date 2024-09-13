package com.example.demo;

public class Article {
	private String url;
	private String header;
	private String content;
	
	public Article(String url, String header, String content) {
		this.url = url;
		this.header = header;
		this.content = content;
	}
	//https://www.youtube.com/watch?v=OxyBRjGfJsk
	//https://www.youtube.com/watch?v=XpMXAxDN7mY
	//implement password encryption using jwt
	// once account system is done, start on email service
	//either rename username field with email and constrict string to be an email
	//or create new field 

	
	public String getUrl() {
		return url;
	}
	public String getHeader() {
		return header;
	}
	public String getContent() {
		return content;
	}
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return url + "\nHeader: " + header + "\n\n" + content; 
	}
	
	
}
