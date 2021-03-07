package controllers.publics;

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
import daos.ItemsDao;
import daos.OrderDao;
import daos.UserDao;
import models.CatPerfume;
import models.Item;
import models.Order;
import models.Perfume;
import models.User;

public class PublicsOrderControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicsOrderControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/public/order.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		CatPerfumeDao catPerfumeDao = new CatPerfumeDao();
		OrderDao orderDao = new OrderDao();
		ItemsDao itemDao = new ItemsDao();
		
		List<CatPerfume> catPFList = catPerfumeDao.findAllBrand();
		request.setAttribute("catPFList", catPFList);
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String adress = request.getParameter("adress");
		String note = request.getParameter("note");
		
		Order order = new Order(new User(userID), phone, email, adress, note);
		int addOrder = orderDao.order(order);
		if(addOrder > 0){
			//lấy sản phẩm
			HttpSession session = request.getSession();
			if(session.getAttribute("order") != null) {
				Order orderItem = (Order) session.getAttribute("order");
				List<Item> ListItem = (List<Item>) orderItem.getItem();
					if(ListItem.size() > 0){
						for(Item item : ListItem){
							int quantity = item.getQuantity();
							long price = item.getPrice();
							int per_id = item.getProduct().getId();
							int order_id = addOrder;
							
							Item items = new Item(new Perfume(per_id), quantity, price, new Order(order_id));
							int addItem = itemDao.add(items);
							if(addItem > 0) {
								response.sendRedirect(request.getContextPath()+"/order?msg=SUCCESS");
								return;
							}else {
								System.out.println("loi items");
							}
						}
					}
			}
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}else {
			System.out.println("lỗi");
			return;
		}
		
	}

}
