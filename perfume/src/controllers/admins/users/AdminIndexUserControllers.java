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
import util.DefineUtil;

public class AdminIndexUserControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexUserControllers() {
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
		CatUserDao catUserDao = new CatUserDao();
		List<CatUser> catUserList = catUserDao.findAll();
		request.setAttribute("catUserList", catUserList);
		
		UserDao userDao = new UserDao();
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		int numberOfItems = userDao.getCount();
		int numberOfPage = (int) Math.ceil((float)numberOfItems / DefineUtil.NUMBER_PER_PAGE_ADMIN);
		if(currentPage > numberOfPage || currentPage < 1) {
			currentPage = 1;
		}
		int offsetAdmin = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE_ADMIN;
		
		List<User> userList = userDao.findAllAdmin(offsetAdmin);
		request.setAttribute("numberOfPage", numberOfPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("userList", userList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
