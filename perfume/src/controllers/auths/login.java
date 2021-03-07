package controllers.auths;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.UserDao;
import models.User;
import util.FileUtil;
import util.StringUtil;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/auth/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		UserDao userDao = new UserDao();
		
		String username = request.getParameter("username");
		String password = StringUtil.md5(request.getParameter("password"));
		
		User userInfor = userDao.login(username,password);
		if(userInfor != null) {
			User user = userDao.viewsALL(username);
			HttpSession session = request.getSession();
			session.setAttribute("userInfor",user);
			User testUser = (User) session.getAttribute("userInfor");
			if(testUser.getCatUser().getId() < 6) {
				response.sendRedirect(request.getContextPath()+"/admin");
				return;
			}else {
				//Chuyển hướng đến trang index admin
				 response.sendRedirect(request.getContextPath()+"/home");
				 return;
			}
		}else {
			System.out.println("Đăng nhập sai.!");
			response.sendRedirect(request.getContextPath()+"/login?msg=ERROR");
			 return;
			 
		}
	}

}
