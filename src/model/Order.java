package model;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Order {

	private long id;
	private LocalDateTime datetime;
	private User user;
	private HashMap<String, Integer> products;
	
	public Order(LocalDateTime datetime, User user, HashMap<String, Integer> products) {
		this.datetime = datetime;
		this.user = user;
		this.products = products;
	}
	
	public Order(long id, LocalDateTime datetime, User user, HashMap<String, Integer> products) {
		this.id = id;
		this.datetime = datetime;
		this.user = user;
		this.products = products;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public LocalDateTime getDatetime() {
		return datetime;
	}
	
	public HashMap<String, Integer> getProducts() {
		return products;
	}
	
}
