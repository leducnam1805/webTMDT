package controllers.publics;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CommentDao;
import models.Comment;

public class PublicShowCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Comment> listCMT = new ArrayList<Comment>();

	public PublicShowCommentController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		CommentDao commentDao = new CommentDao();

		int idpro = 0;
		try {
			idpro = Integer.parseInt(request.getParameter("idpro"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/index.jsp");
			rd.forward(request, response);
			return;
		}
		String fullname = request.getParameter("fullname");
		String addComment = request.getParameter("addComment");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String create_comment = sdf.format(new Date());
		
		Comment addCmt = new Comment(fullname, addComment, idpro);
		int add = commentDao.add(addCmt);
		
		Comment objCmt = new Comment(fullname, create_comment, addComment);
		listCMT.add(objCmt);
		HttpSession session = request.getSession();
		session.setAttribute("listCMT", listCMT);
		if (listCMT != null) {
			for (Comment itemCmt : listCMT) {
				response.getWriter().print("<li class=\"media\">" + "<div class=\"media-body\">"
						+ "<div class=\"well well-lg\">" + "<h4 class=\"media-heading text-uppercase reviews\">"
						+ itemCmt.getName_comment() + "</h4>"
						+ "<ul class=\"media-date text-uppercase reviews list-inline\">" + "<li class=\"dd\">"
						+ itemCmt.getCreate_comment() + "</li>" + "</ul>" + "<p class=\"media-comment\">"
						+ itemCmt.getMessage() + "</p>"
						+ "<a class=\"btn btn-info btn-circle text-uppercase\" href=\"#\" id=\"reply\"><span class=\"glyphicon glyphicon-share-alt\"></span> Reply</a>"
						+ "<a class=\"btn btn-warning btn-circle text-uppercase\" data-toggle=\"collapse\" href=\"#replyOne\"><span class=\"glyphicon glyphicon-comment\"></span> 2 comments</a>"
						+ "</div>" + "</div>" + "</li>");
			}
		}

	}

}
