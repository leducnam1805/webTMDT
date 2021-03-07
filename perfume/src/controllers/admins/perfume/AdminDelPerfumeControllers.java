package controllers.admins.perfume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import constants.GlobalConstants;
import daos.CatPerfumeDao;
import daos.PerfumeDao;
import daos.PictureDao;
import models.CatPerfume;
import models.Perfume;
import models.Picture;
import util.FileUtil;

@MultipartConfig
public class AdminDelPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final File filename = null;

	public AdminDelPerfumeControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		PerfumeDao product = new PerfumeDao();
		PictureDao picture = new PictureDao();
		int idPF = 0;
		try {
			idPF = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		int delPic = picture.delPic(idPF);
		if (delPic > 0) {
			List<Picture> pic = picture.viewsDELS(idPF);
			for(Picture objPic : pic) {
				String fileName = objPic.getPicture();
				FileUtil.delFile(fileName, request);
			}
		}
		int del = product.del(idPF);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/perfume/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
