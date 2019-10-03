package model.db;

import java.security.spec.RSAKeyGenParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import model.Order;
import model.Product;
import model.User;

public class OrderDao {

	private static OrderDao instance;
	private OrderDao(){}
	
	public static synchronized OrderDao getInstance(){
		if(instance == null){
			instance = new OrderDao();
		}
		return instance;
	}
	
	public HashSet<Order> getOrdersForUser(User u) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT id, date FROM orders WHERE user_id = ? ORDER BY date");
		ps.setLong(1, u.getId());
		ResultSet rs = ps.executeQuery();
		LinkedHashSet<Order> orders = new LinkedHashSet<>();
		while(rs.next()){
			HashMap<String, Integer> products = ProductDao.getInstance().getProductsForOrder(rs.getLong("id"));
			orders.add(new Order(rs.getTimestamp("date").toLocalDateTime(), u, products));
		}
		return orders;
	}
	
}
