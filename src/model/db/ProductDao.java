package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import model.Product;

public class ProductDao {

	private static ProductDao instance;
	private ProductDao(){}
	private static final HashSet<Product> products = new HashSet<>();//cashing products
	
	public static synchronized ProductDao getInstance(){
		if(instance == null){
			instance = new ProductDao();
		}
		return instance;
	}

	public void insertProduct(Product p) throws SQLException{
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, p.getName());
		ps.setInt(2, p.getQuantity());
		ps.setDouble(3, p.getPrice());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		p.setId(rs.getLong("id"));
		if(!products.isEmpty()){
			products.add(p);
		}
	}
	
	public HashSet<Product> getAllProducts() throws SQLException{
		if(!products.isEmpty()){
			return products;
		}
		else{
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT id, name, quantity, price FROM products");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				products.add(new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("quantity"), rs.getDouble("price")));
			}
			return products;
		}
	}

	public HashMap<String, Integer> getProductsForOrder(long orderId) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT p.name as name, o.quantity as quantity FROM orders_have_products as "
													+ "o JOIN products as p ON "
													+ "o.product_id = p.id WHERE "
													+ "o.order_id = ? ORDER BY quantity;");
		ps.setLong(1, orderId);
		ResultSet rs = ps.executeQuery();
		LinkedHashMap<String, Integer> productsForOrder = new LinkedHashMap<String, Integer>();
		while(rs.next()){
			productsForOrder.put(rs.getString("name"), rs.getInt("quantity"));
		}
		return productsForOrder;
	}

}
