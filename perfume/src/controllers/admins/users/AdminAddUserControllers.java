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

public class AdminAddUserControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddUserControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		CatUserDao catUserDao = new CatUserDao();
		List<CatUser> catUserList = catUserDao.findAll();
		request.setAttribute("catUserList", catUserList);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		//categories user
		CatUserDao catUserDao = new CatUserDao();
		List<CatUser> catUserList = catUserDao.findAll();
		request.setAttribute("catUserList", catUserList);
		
		//user
		UserDao userDao = new UserDao();
		
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String pass = request.getParameter("password");
		String money = request.getParameter("money");
		
		//check validate
		User test = userDao.test(username);
		if("".equals(username)) {
			request.setAttribute("err", "Vui lòng nhập tên đăng nhập");
			doRun(request, response);
			return;
		}else if(username.length() < 3) {
			request.setAttribute("err", "Tên quá ngắn. Vui lòng nhập lại");
			doRun(request, response);
			return;
		}else if(test != null){
			request.setAttribute("err", "Tên đăng nhập "+username+" đã có. Vui lòng nhập tên khác");
			doRun(request, response);
			return;
		}else {
			if("".equals(fullname)) {
				request.setAttribute("err", "Vui lòng nhập họ và tên");
				doRun(request, response);
				return;
			}else if(fullname.length() < 3){
				request.setAttribute("err", "Tên quá ngắn. Vui lòng nhập lại");
				doRun(request, response);
				return;
			}
		}
		int catUser = 0;
		try {
			catUser = Integer.parseInt(request.getParameter("catUser"));
			if(catUser < 1) {
				request.setAttribute("err", "Vui lòng chọn loại tài khoản");
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		if("".equals(pass)) {
			request.setAttribute("err", "Ơ...Chưa nhập mật khẩu");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}else if(pass.length() < 5) {
			request.setAttribute("err", "Ngắn quá. Nhập thêm đi");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
		}
		// md5 password
		String password = StringUtil.md5(pass);
		//set data
		request.setAttribute("username", username);
		request.setAttribute("fullname", fullname);
		request.setAttribute("money", money);
		request.setAttribute("catUser", catUser);
		
		User user = new User(username,fullname,password,money, new CatUser(catUser));
		int add = userDao.add(user);
		if(add > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/user?msg=OK");
			return;
		}
	}
	protected void doRun(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/add.jsp");
			rd.forward(request, response);
			return;
	}
}
