package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User)request.getSession().getAttribute("user");
		String avatar = u.getAvatarUrl();
		if(avatar == null){
			avatar = "default.jpg";
		}
		File myFile = new File(RegisterServlet.AVATAR_URL+avatar);
		
		try (OutputStream out = response.getOutputStream()) {
		    Path path = myFile.toPath();
		    Files.copy(path, out);
		    out.flush();
		} catch (IOException e) {
		   System.out.println("ops");
		}
	}


}
