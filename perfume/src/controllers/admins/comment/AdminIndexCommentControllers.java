package controllers.admins.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CommentDao;
import models.Comment;

public class AdminIndexCommentControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexCommentControllers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfor") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		CommentDao commentDao = new CommentDao();
		
		List<Comment> commnetList = commentDao.findAll();
		request.setAttribute("commnetList", commnetList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/comment/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
