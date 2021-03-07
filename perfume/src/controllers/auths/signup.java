package controllers.auths;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UserDao;
import models.User;
import util.FileUtil;
import util.StringUtil;

public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public signup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("views/auth/singup.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		UserDao userDao = new UserDao();
		
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		
		User user = new User(username,fullname, password);
		int singup = userDao.signup(user);
		if(singup > 0) {
			 response.sendRedirect(request.getContextPath()+"/login?msg=SUCCESS");
			 return;
		}else {
			response.sendRedirect(request.getContextPath()+"/signup?msg=ERROR");
			return;
		}
	}

}
