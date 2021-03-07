package controllers.admins.order;

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
import models.Item;

public class AdminViewOrderControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminViewOrderControllers() {
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
		
		ItemsDao itemDao = new ItemsDao();
		
		int idOrder = 0;
		try {
			idOrder = Integer.parseInt(request.getParameter("idOrder"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		Item item = itemDao.viewOrder(idOrder);
		int quantity = item.getQuantity();
		Long money = item.getPrice();
		Long totalMoney = money * quantity;
			response.getWriter().print("<table class=\"table table-bordered table-striped\">\r\n" + 
					"			  <tr>\r\n" + 
					"			  	<th>Tên sản phẩm</th>\r\n" +
					"			  	<td>"+item.getProduct().getPerfumes()+"</td>\r\n" + 
					"			  </tr>\r\n" + 
					"			  <tr>\r\n" + 
					"			  	<th>Người mua</th>\r\n" +
					"			  	<td>"+item.getOrder().getCustomer().getFullname()+"</td>\r\n" + 
					"			  </tr>\r\n" + 
					"			  <tr>\r\n" + 
					"			  	<th>Số lượng</th>\r\n" +
					"			  	<td>"+item.getQuantity()+"</td>\r\n" + 
					"			  </tr>\r\n" + 
					"			  <tr>\r\n" + 
					"			  	<th>Giá tiền</th>\r\n" +
					"			  	<td>"+totalMoney+"</td>\r\n" + 
					"			  </tr>\r\n" +
					"			  <tr>\r\n" + 
					"			  	<th>Mô tả</th>\r\n" +
					"			  	<td>"+item.getProduct().getDescription()+"</td>\r\n" + 
					"			  </tr>\r\n" +
					"			  <tr>\r\n" + 
					"			  	<th>Xuất sứ</th>\r\n" +
					"			  	<td>"+item.getProduct().getMade()+"</td>\r\n" + 
					"			  </tr>\r\n" +
					"			  <tr>\r\n" + 
					"			  	<th>Địa chỉ</th>\r\n" +
					"			  	<td>"+item.getOrder().getAdress()+"</td>\r\n" + 
					"			  </tr>\r\n" +
					"			  <tr>\r\n" + 
					"			  	<th>Số liên lạc</th>\r\n" +
					"			  	<td>"+item.getOrder().getPhone()+"</td>\r\n" + 
					"			  </tr>\r\n" +
					"	       	</table>");
		}
		

}
