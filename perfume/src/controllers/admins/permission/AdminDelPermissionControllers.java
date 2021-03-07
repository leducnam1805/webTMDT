package controllers.admins.permission;

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
import daos.PerfumeDao;
import daos.PermissionDao;
import models.CatUser;
import models.Perfume;
import models.Permission;

public class AdminDelPermissionControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelPermissionControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		
		CatUserDao catUserDao = new CatUserDao();
		
		Boolean delquyen = Boolean.parseBoolean(request.getParameter("giatri"));
		int idquyen = 0;
		try {
			idquyen = Integer.parseInt(request.getParameter("delquyen"));
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		CatUser catUer = new CatUser(idquyen, true, true, delquyen);
		int del = catUserDao.delquyen(catUer);
		if(del > 0) {
			System.out.println("OK");
		}else {
			System.out.println("loi");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/permission/index.jsp");
		rd.forward(request, response);
	}

}
