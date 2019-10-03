package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class User {

	private long id;
	private String username;
	private String password;
	private String email;
	private String avatarUrl;
	private Set<Order> orders;

	public User(long id, String username, String password, String email, String avatarUrl) {
		this(username, password, email, avatarUrl);
		this.id = id;
	}
	
	public User(String username, String password, String email, String avatarUrl) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.avatarUrl = avatarUrl;
	}
	
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Set<Order> getOrders() {
		return Collections.unmodifiableSet(orders);
	}
	
	public String getAvatarUrl() {
		return avatarUrl;
	}
	
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
