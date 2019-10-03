package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.User;
import model.db.ProductDao;
import model.db.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check for login credentials
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		//check if user exists in db
		try {
			boolean exists = UserDao.getInstance().existsUser(username, password);
			if(exists){
				//update session
				User u = UserDao.getInstance().getUser(username);
				request.getSession().setAttribute("user", u);
				ServletContext application = getServletConfig().getServletContext();
				synchronized (application) {
					if(application.getAttribute("products") == null){
						HashSet<Product> products = ProductDao.getInstance().getAllProducts();
						application.setAttribute("products", products);
					}
				}
				request.getRequestDispatcher("main.jsp").forward(request, response);
			}
			else{
				request.setAttribute("error", "user does not exist");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("error", "database problem : " + e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		
		}
	}

}
