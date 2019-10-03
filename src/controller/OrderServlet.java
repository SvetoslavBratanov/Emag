package controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.User;

@WebServlet("/sortOrders")
public class OrderServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sort = request.getParameter("sort");
		Object o = request.getSession().getAttribute("user");
		if(o == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		User u = (User) o;
		TreeSet<Order> set = new TreeSet<>(new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				if(sort.equals("asc")){
					return o1.getDatetime().compareTo(o2.getDatetime());
				}
				else{
					return o2.getDatetime().compareTo(o1.getDatetime());
				}
			}
		});
		set.addAll(u.getOrders());
		u.setOrders(set);
		request.getRequestDispatcher("orders.jsp").forward(request, response);
	}

}
