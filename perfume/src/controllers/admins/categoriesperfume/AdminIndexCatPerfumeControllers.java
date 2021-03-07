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

/*import com.sun.webkit.ContextMenu.ShowContext;*/

import daos.CatPerfumeDao;
import models.CatPerfume;
import util.CategoriesUtil;

public class AdminIndexCatPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexCatPerfumeControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		CatPerfumeDao catPerfumeDao = new CatPerfumeDao();
		
		//Thương hiệu
		List<CatPerfume> catPFList = catPerfumeDao.findAllBrand();
		request.setAttribute("catPFList", catPFList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
