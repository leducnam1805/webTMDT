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

public class AdminIndexPermissionControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexPermissionControllers() {
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
		List<CatUser> catUserList = catUserDao.findAllQuyen();
		request.setAttribute("catUserList", catUserList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/permission/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
