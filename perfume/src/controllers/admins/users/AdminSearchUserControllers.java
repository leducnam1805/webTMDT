package controllers.admins.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CatUserDao;
import daos.UserDao;
import models.CatUser;
import models.User;
import util.StringUtil;

public class AdminSearchUserControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminSearchUserControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		//cat user
		CatUserDao catUSDao = new CatUserDao();
		List<CatUser> catUserList = catUSDao.findAll();
		request.setAttribute("catUserList", catUserList);
		
		//user
		UserDao userDao = new UserDao();
		int catUser = 0;
		try {
			catUser = Integer.parseInt(request.getParameter("catUser"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		String nameUser = request.getParameter("nameUser");
		if(catUser > 0) {
			List<User> item = userDao.searchIDCat(catUser);
			request.setAttribute("userList", item);
		}else if(!"".equals(nameUser)) {
			List<User> item = userDao.searchName(nameUser);
			request.setAttribute("userList", item);
		}else if(catUser > 0 && !"".equals(nameUser)) {
			List<User> item = userDao.searchIDName(catUser,nameUser);
			request.setAttribute("userList", item);
		}else {
			List<User> userList = userDao.findAll();
			request.setAttribute("userList", userList);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
