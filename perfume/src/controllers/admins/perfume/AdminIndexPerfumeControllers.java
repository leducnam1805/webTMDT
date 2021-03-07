package controllers.admins.perfume;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.PerfumeDao;
import models.CatPerfume;
import models.Perfume;

public class AdminIndexPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexPerfumeControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		PerfumeDao perDao = new PerfumeDao();
		
		List<Perfume> perList = perDao.findAll();
		request.setAttribute("perList", perList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/perfume/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
