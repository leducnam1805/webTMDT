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

public class AdminEditCategoriesUserControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCategoriesUserControllers() {
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		CatUser item = catUserDao.viewsEdit(id);
		request.setAttribute("catUser", item);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		CatUserDao catUserDao = new CatUserDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		String name = request.getParameter("catUser");
		CatUser catUser = new CatUser(id,name);
		if("".equals(name)) {
			request.setAttribute("err", "Tên loại người dùng không được bỏ trống");
			request.setAttribute("catUser", catUser);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/edit.jsp");
			rd.forward(request, response);
			return;
		}else if(name.length() < 3){
			request.setAttribute("err", "Tên quá ngắn. Vui lòng nhập lại");
			request.setAttribute("catUser", catUser);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/edit.jsp");
			rd.forward(request, response);
			return;
		}else {
			CatUser test = catUserDao.test(id,name);
			if(test != null) {
				request.setAttribute("err", "Loại người dùng "+"<span style='color:yellow;'>"+name+"</span>"+" đã có. Vui lòng nhập tên khác");
				request.setAttribute("catUser", catUser);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesuser/edit.jsp");
				rd.forward(request, response);
				return;
			}else {
				int edit = catUserDao.edit(catUser);
				if(edit > 0) {
					response.sendRedirect(request.getContextPath()+"/admin/categoriesuser?msg=OK");
					return;
				}
			}
		}
	}

}
