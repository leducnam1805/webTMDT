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
import models.CatPerfume;

public class AdminEditCatPerfumeControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatPerfumeControllers() {
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
		
		//loại
		List<CatPerfume> catPFList = catPFDao.findAllBrandN();
		request.setAttribute("catPFList", catPFList);
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		CatPerfume catPF = catPFDao.viewID(id);
		request.setAttribute("catPerfume", catPF);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		int catPFE = 0;
		try {
			catPFE = Integer.parseInt(request.getParameter("catPF"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		CatPerfumeDao catPFDao = new CatPerfumeDao();
//		//handle
		String name = request.getParameter("name");
		if("".equals(name)) {
			request.setAttribute("err", "Tên loại nước hoa không được bỏ trống");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/add.jsp");
			rd.forward(request, response);
			return;
		}else if(name.length() < 3){
			request.setAttribute("err", "Tên quá ngắn. Vui lòng nhập lại");
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/add.jsp");
			rd.forward(request, response);
			return;
		}else {
			CatPerfume test = catPFDao.testID(id,name);
			if(test != null) {
				request.setAttribute("err", "Loại nước hoa "+"<span style='color:yellow;'>"+name+"</span>"+" đã có. Vui lòng nhập tên khác");
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoriesperfume/add.jsp");
				rd.forward(request, response);
				return;
			}else {
				CatPerfume catPF = new CatPerfume(id,name,catPFE);
				int edit = catPFDao.edit(catPF);
				if(edit > 0) {
					response.sendRedirect(request.getContextPath()+"/admin/catperfume?msg=OK");
					return;
				}
			}
		}
	
	}

}
