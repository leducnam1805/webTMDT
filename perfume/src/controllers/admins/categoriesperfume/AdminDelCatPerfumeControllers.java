package controllers.admins.categoriesperfume;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CatPerfumeDao;
import daos.PerfumeDao;
import models.CatPerfume;
import models.CatUser;

public class AdminDelCatPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDelCatPerfumeControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		CatPerfumeDao catPFDao = new CatPerfumeDao();
		PerfumeDao perfumeDao = new PerfumeDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		System.out.println("id: "+id);
		int delPF = perfumeDao.delCatPF(id);
		if(delPF > 0) {
			int del = catPFDao.del(id);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
