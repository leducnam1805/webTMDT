package controllers.admins.categoriesperfume;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CatPerfumeDao;
import models.CatPerfume;

public class AdminAddCatPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddCatPerfumeControllers() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		CatPerfumeDao catPFDao = new CatPerfumeDao();
		//loại
		List<CatPerfume> catPFList = catPFDao.findAllBrandN();
		request.setAttribute("catPFList", catPFList);
		
		//handle
		String name = request.getParameter("name");
		int catPFL = 0;
		try {
			catPFL = Integer.parseInt(request.getParameter("catPF"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		if("".equals(name)) {
			request.setAttribute("err", "Tên thương hiệu không được bỏ trống");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/add.jsp");
			rd.forward(request, response);
			return;
		}else if(name.length() < 3){
			request.setAttribute("err", "Tên quá ngắn. Vui lòng nhập lại");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/add.jsp");
			rd.forward(request, response);
			return;
		}else {
			CatPerfume test = catPFDao.test(name);
			if(test != null) {
				request.setAttribute("err", "Thương hiệu "+"<span style='color:yellow;'>"+name+"</span>"+" đã có. Vui lòng nhập tên khác");
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/add.jsp");
				rd.forward(request, response);
				return;
			}else {
				CatPerfume catPF = new CatPerfume(name,catPFL);
				int add = catPFDao.add(catPF);
				if(add > 0) {
					response.sendRedirect(request.getContextPath()+"/admin/catperfume?msg=OKL");
					return;
				}
			}
		}
	}

}
