package controllers.admins.categoriesuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CatUserDao;
import models.CatUser;

public class AdminAddCategoriesUserControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCategoriesUserControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		CatUserDao catUserDao = new CatUserDao();
		//handle
		String name = request.getParameter("name");
		if("".equals(name)) {
			request.setAttribute("err", "Tên loại người dùng không được bỏ trống");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/add.jsp");
			rd.forward(request, response);
			return;
		}else if(name.length() < 3){
			request.setAttribute("err", "Tên quá ngắn. Vui lòng nhập lại");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/add.jsp");
			rd.forward(request, response);
			return;
		}else {
			CatUser test = catUserDao.test(name);
			if(test != null) {
				request.setAttribute("err", "Loại người dùng "+"<span style='color:yellow;'>"+name+"</span>"+" đã có. Vui lòng nhập tên khác");
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/add.jsp");
				rd.forward(request, response);
				return;
			}else {
				CatUser catUser = new CatUser(name);
				int add = catUserDao.add(catUser);
				if(add > 0) {
					response.sendRedirect(request.getContextPath()+"/admin/categoriesuser?msg=OK");
					return;
				}
			}
		}
	}

}
