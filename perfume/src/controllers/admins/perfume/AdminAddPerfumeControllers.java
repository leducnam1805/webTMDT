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
public class AdminAddPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final File filename = null;

	public AdminAddPerfumeControllers() {
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
		List<CatPerfume> catPFList = catPFDao.findAllBrandN();
		request.setAttribute("catPFList", catPFList);

		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/perfume/add.jsp");
		rd.forward(request, response);
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
		
		CatPerfumeDao catPFDao = new CatPerfumeDao();
		List<CatPerfume> catPFList = catPFDao.findAllBrandN();
		request.setAttribute("catPFList", catPFList);
		
		PictureDao pitureDao = new PictureDao();
		PerfumeDao perfumeDao = new PerfumeDao();

		String namePF = request.getParameter("name");
		String desciption = request.getParameter("desciption");
		String detail = request.getParameter("detail");
		String brand = request.getParameter("brand");
		String made = request.getParameter("made");
		String capacity = request.getParameter("capacity");
		String codePF = request.getParameter("codePF");
		int quantum = 0;
		try {
			quantum = Integer.parseInt(request.getParameter("quantum"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		Long money = Long.parseLong(request.getParameter("money"));
		int catPF = 0;
		try {
			catPF = Integer.parseInt(request.getParameter("catPF"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}

		Perfume perfume = new Perfume(namePF, desciption, detail, brand, made, capacity, codePF, quantum, money,
				 new CatPerfume(catPF));
		int addPF = perfumeDao.add(perfume);
		if (addPF > 0) {
			// upload multiple images
			PictureDao pictureDao = new PictureDao();
			String fileName = "";
			List<String> listPicture = new ArrayList<String>();
			List<Part> fileParts = request.getParts().stream().filter(part -> "picture".equals(part.getName()))
					.collect(Collectors.toList());
			if (fileParts != null && fileParts.size() > 0 && (fileParts.size() <= 5 || fileParts.size() < 1)) {
				for (Part filePart : fileParts) {
					fileName = FileUtil.rename(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
					// InputStream fileContent = filePart.getInputStream();
					// System.out.println("fileContent: "+fileContent);
					if (!"".equals(fileName)) {
						// lấy đường dẫn thực của dự án
						String dirPath = request.getServletContext().getRealPath("") + GlobalConstants.DIR_UPLOAD;
						File saveDir = new File(dirPath);
						if (!saveDir.exists()) {
							saveDir.mkdirs();
						}
						String filePath = dirPath + File.separator + fileName;
						filePart.write(filePath);
					}
					int addImage = pictureDao.add(fileName,addPF);
				}
			} else {
				request.setAttribute("err", "Vui lòng chọn hình ảnh ( Tối đa 5 hình cho mỗi sản phẩm )");
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/perfume/add.jsp");
				rd.forward(request, response);
				return;
			}
			response.sendRedirect(request.getContextPath() + "/admin/perfume?msg=OK");
			return;
		}
	}
}
