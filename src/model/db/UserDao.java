package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import model.Order;
import model.User;
import util.Encrypter;

public class UserDao {
	private static UserDao instance;
	private UserDao(){}
	
	public static synchronized UserDao getInstance(){
		if(instance == null){
			instance = new UserDao();
		}
		return instance;
	}
	
	public void insertUser(User u) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, email, avatar_url) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, u.getUsername());
		ps.setString(2, Encrypter.encrypt(u.getPassword()));
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getAvatarUrl());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		u.setId(rs.getLong(1));
	}
	
	public boolean existsUser(String username, String password) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT count(*) as count FROM users WHERE username = ? AND password = ?");
		ps.setString(1, username);
		ps.setString(2, Encrypter.encrypt(password));
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt("count") > 0;
	}
	
	public User getUser(String username) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT id, username, password, email, avatar_url FROM users WHERE username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		rs.next();
		User u = new User(
				rs.getLong("id"), 
				username, 
				rs.getString("password"), 
				rs.getString("email"),
				rs.getString("avatar_url"));

		HashSet<Order> orders = OrderDao.getInstance().getOrdersForUser(u);
		u.setOrders(orders);
		return u;
	}
}
