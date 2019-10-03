package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import model.User;
import model.db.UserDao;

@WebServlet("/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	
	public static final String AVATAR_URL = "D:\\Projects\\Java Projects\\Emag-2\\WebContent\\resources\\";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check for register credentials
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		String password2 = request.getParameter("pass2");
		String email = request.getParameter("email");
		Part avatarPart = request.getPart("avatar");
		InputStream fis = avatarPart.getInputStream();
		File myFile = new File(AVATAR_URL+username+".jpg");
		if(!myFile.exists()){
			myFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(myFile);
		int b = fis.read();
		while(b != -1){
			fos.write(b);
			b = fis.read();
		}
		fis.close();
		fos.close();
		String avatarUrl = username+".jpg";
	
		if(!password.equals(password2)){
			request.setAttribute("error", "passwords missmatch");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		try {
			if(!UserDao.getInstance().existsUser(username, password)){
				User u = new User(username, password, email, avatarUrl);
				UserDao.getInstance().insertUser(u);
				request.getSession().setAttribute("user", u);
				request.getRequestDispatcher("main.jsp").forward(request, response);
			}
			else{
				request.setAttribute("error", "user already registerred");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			request.setAttribute("error", "database problem : " + e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
