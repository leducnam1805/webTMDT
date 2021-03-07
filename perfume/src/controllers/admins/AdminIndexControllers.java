package controllers.admins;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ContactDao;
import daos.OrderDao;
import daos.PerfumeDao;
import daos.UserDao;

public class AdminIndexControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexControllers() {
		super();
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		//product
		PerfumeDao perfumeDao = new PerfumeDao();
		int countPer = perfumeDao.countID();
		request.setAttribute("countPer", countPer);
		
		//order
		OrderDao orderDao = new OrderDao();
		int countOrder = orderDao.countID();
		request.setAttribute("countOrder", countOrder);
		
		//user
		UserDao userDao = new UserDao();
		int countUser = userDao.countID();
		request.setAttribute("countUser", countUser);
		
		//contact
		ContactDao contactDao = new ContactDao();
		int countContact = contactDao.countID();
		request.setAttribute("countContact", countContact);
		
		RequestDispatcher rd = request.getRequestDispatcher("views/admin/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
