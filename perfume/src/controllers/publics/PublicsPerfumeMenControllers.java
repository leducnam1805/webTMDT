package controllers.publics;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CatPerfumeDao;
import daos.PerfumeDao;
import daos.PictureDao;
import models.CatPerfume;
import models.Perfume;
import models.Picture;

public class PublicsPerfumeMenControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicsPerfumeMenControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CatPerfumeDao catPerfumeDao = new CatPerfumeDao();
		List<CatPerfume> catPFList = catPerfumeDao.findAllBrand();
		request.setAttribute("catPFList", catPFList);
		
		PerfumeDao productDao = new PerfumeDao();
		PictureDao pictureDao = new PictureDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		Perfume product = productDao.viewsCatID(id);
		request.setAttribute("product", product);
		
		List<Perfume> allPro = productDao.findCatID(id);
		for(Perfume allProduct : allPro) {
			int idPro = allProduct.getId();
			Picture picPro = pictureDao.viewsIDPF2(idPro);
			request.setAttribute("picPro" + allProduct.getId(), picPro);
		}
		request.setAttribute("allPro", allPro);
		
		List<Perfume> allNew = productDao.findNewID(id);
		for(Perfume allProNew : allNew) {
			int idProNew = allProNew.getId();
			Picture picProNew = pictureDao.viewsIDPF2(idProNew);
			request.setAttribute("picProNew" + allProNew.getId(), picProNew);
		}
		request.setAttribute("allNew", allNew);
		
		List<Perfume> allView = productDao.findAllView(id);
		for(Perfume allProView : allView) {
			int idProView = allProView.getId();
			Picture picProView = pictureDao.viewsIDPF2(idProView);
			request.setAttribute("picProView" + allProView.getId(), picProView);
		}
		request.setAttribute("allView", allView);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/perfume-men.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
