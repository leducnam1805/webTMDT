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
import daos.PictureDao;
import models.CatPerfume;
import models.Perfume;
import models.Picture;

public class AdminViewsPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminViewsPerfumeControllers() {
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
		PictureDao pictureDao = new PictureDao();

		int idPF = 0;
		try {
			idPF = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		Perfume product = perDao.viewsID(idPF);
		
		List<Picture> image = pictureDao.viewsID(idPF);
		
		request.setAttribute("product", product);
		request.setAttribute("image", image);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/perfume/views.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
