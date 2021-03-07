package controllers.admins.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.OrderDao;
import models.Order;

public class AdminStatusOrderControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminStatusOrderControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		
		OrderDao orderDao = new OrderDao();
		
		int idPer = 0;
		try {
			idPer = Integer.parseInt(request.getParameter("idPer"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		Boolean status = Boolean.parseBoolean(request.getParameter("giatri"));
		Order order = new Order(idPer, status);
		int addStatus = orderDao.addStatus(order);
		if(addStatus > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/order");
			return;
		}
		
	}

}
