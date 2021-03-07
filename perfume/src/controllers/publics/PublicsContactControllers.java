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
import daos.ContactDao;
import models.CatPerfume;
import models.Contact;

public class PublicsContactControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicsContactControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CatPerfumeDao catPerfumeDao = new CatPerfumeDao();
		List<CatPerfume> catPFList = catPerfumeDao.findAllBrand();
		request.setAttribute("catPFList", catPFList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		ContactDao contactDao = new ContactDao();
		// get data
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");
		
		//set data
		request.setAttribute("name", name);
		request.setAttribute("message", message);
		
		//check data
		if("".equals(name)) {
			request.setAttribute("err", "Vui lòng nhập tên");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
			rd.forward(request, response);
			return;
		}else if("".equals(message)) {
			request.setAttribute("err", "Bạn có điều gì muốn nói.");
			RequestDispatcher rd = request.getRequestDispatcher("/views/public/contact.jsp");
			rd.forward(request, response);
			return;
		}else {
			Contact contact = new Contact(name, email, phone, message);
			int sendData = contactDao.sendData(contact);
			if(sendData > 0) {
				response.sendRedirect(request.getContextPath()+"/contact?msg=OK");
				return;
			}
		}
	}

}
