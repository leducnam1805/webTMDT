package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import models.CatPerfume;
import models.Perfume;
import models.Picture;
import util.DBConnectionUtil;

public class PictureDao extends AbstractDAO {

	public int add(String fileName, int addPF) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO pic_per(picture,per_id) VALUES(?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, fileName);
			pst.setInt(2, addPF);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Picture> viewsID(int idPF) {
		List<Picture> pictureList = new ArrayList<Picture>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM pic_per WHERE per_id = ?";
		Picture picture = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idPF);
			rs = pst.executeQuery();
			while(rs.next()) {
				picture = new Picture(rs.getInt("id"), 
						rs.getString("picture"), 
						new Perfume(rs.getInt("per_id")));
				pictureList.add(picture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pictureList;
	}

	public int del(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM pic_per WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Picture viewsDEL(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM pic_per WHERE id = ?";
		Picture picture = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				picture = new Picture(rs.getInt("id"),
						rs.getString("picture"),
						new Perfume(rs.getInt("per_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return picture;
	}

	public int delPic(int idPF) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM pic_per WHERE per_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idPF);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Picture> viewsDELS(int idPF) {
		List<Picture> picList = new ArrayList<Picture>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM pic_per WHERE per_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idPF);
			rs = pst.executeQuery();
			while(rs.next()) {
				Picture objPic = new Picture(rs.getInt("id"),
						rs.getString("picture"), 
						new Perfume(rs.getInt("per_id")));
				picList.add(objPic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return picList;
	}

	public List<Picture> findAll() {
		List<Picture> picList = new ArrayList<Picture>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT pic_per.*,perfumes.*,cat_perfume.*"
				+ " FROM pic_per"
				+ " INNER JOIN perfumes ON pic_per.per_id = perfumes.id"
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Picture pic = new Picture(rs.getInt("id"), rs.getString("picture"),
						new Perfume(rs.getInt("per_id"), rs.getString("name_per"), rs.getString("description"),
								rs.getString("detail"), rs.getString("brand"), rs.getString("made"),
								rs.getString("capacity"), rs.getString("code_per"), rs.getInt("amount"),
								rs.getLong("money"), rs.getInt("evaluate"), rs.getTimestamp("create_date"),
								rs.getInt("view"), 
								new CatPerfume(rs.getInt("cat_perfume"),rs.getString("catperfume"),
										rs.getInt("parrent_id"))));
				picList.add(pic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return picList;
	}

	public List<Picture> viewsIDPF(int idPF) {
		List<Picture> arPic = new ArrayList<Picture>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM pic_per WHERE per_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idPF);
			rs = pst.executeQuery();
			while(rs.next()) {
				Picture obj = new Picture(rs.getInt("id"), rs.getString("picture"));
				arPic.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arPic;
	}
	
	public Picture viewsIDPF2(int idPF) {
		Picture obj = null;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM pic_per WHERE per_id = ? LIMIT 1";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idPF);
			rs = pst.executeQuery();
			if(rs.next()) {
				obj = new Picture(rs.getInt("id"), rs.getString("picture"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
