package controllers.admins.picture;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CatPerfumeDao;
import daos.PictureDao;
import models.CatPerfume;
import models.CatUser;
import models.Picture;
import util.FileUtil;

public class AdminEditPictureControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditPictureControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PictureDao picDao = new PictureDao();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		// Kiểm tra nếu có thì xóa trong thư mục
		Picture picture = picDao.viewsDEL(id);
		String img = picture.getPicture();
		FileUtil.delFile(img, request);
		//xóa ở data
		int del = picDao.del(id);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/perfume/index.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
