package controllers.admins.banking;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ItemsDao;
import daos.PerfumeDao;
import models.Item;

public class AdminIndexBankingControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexBankingControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		ItemsDao itemDao = new ItemsDao();
		List<Item> listItem = itemDao.findAllBanking();
		
		request.setAttribute("listItem",listItem);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/banking/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
