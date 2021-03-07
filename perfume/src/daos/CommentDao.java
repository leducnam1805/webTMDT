package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Comment;
import util.DBConnectionUtil;

public class CommentDao extends AbstractDAO {

	public List<Comment> findAll() {
		List<Comment> list = new ArrayList<Comment>();
		String sql = "SELECT * FROM comment ORDER by id";
		con = DBConnectionUtil.getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Comment objCommnet = new Comment(rs.getInt("id"),
						rs.getString("name_comment"),
						rs.getString("message"));
				list.add(objCommnet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int add(Comment objCmt) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO comment(name_comment,message,id_per) VALUES(?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, objCmt.getName_comment());
			pst.setString(2, objCmt.getMessage());
			pst.setInt(3, objCmt.getId_per());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int del(int idCMT) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM comment WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idCMT);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
