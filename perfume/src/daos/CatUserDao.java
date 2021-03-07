package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CatUser;
import util.DBConnectionUtil;

public class CatUserDao extends AbstractDAO {
	
	public List<CatUser> findAll(){
		List<CatUser> list = new ArrayList<CatUser>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_user order by id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				CatUser catUser = new CatUser(rs.getInt("id"),
						rs.getString("catUser"));
				list.add(catUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int add(CatUser catUser) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO cat_user(catuser) VALUES(?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, catUser.getCatUser());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public CatUser viewsEdit(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_user WHERE id = ?";
		CatUser objCatUser = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				objCatUser = new CatUser(rs.getInt("id"),
						rs.getString("catuser"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objCatUser;
	}

	public int edit(CatUser catUser) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE cat_user SET catuser = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, catUser.getCatUser());
			pst.setInt(2, catUser.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int del(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM cat_user"
				+ " WHERE cat_user.id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public CatUser test(int id,String name) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_user WHERE catuser = ? AND id != ?";
		CatUser objCU = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				objCU = new CatUser(rs.getInt("id"),
						rs.getString("catuser"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objCU;
	}

	public CatUser test(String name) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_user WHERE catuser = ? ";
		CatUser objCU = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if(rs.next()) {
				objCU = new CatUser(rs.getInt("id"),
						rs.getString("catuser"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objCU;
	}

	public List<CatUser> findAllQuyen() {
		List<CatUser> list = new ArrayList<CatUser>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_user order by id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				CatUser catUser = new CatUser(rs.getInt("id"),
						rs.getString("catuser"),
						rs.getBoolean("addquyen"),
						rs.getBoolean("editquyen"),
						rs.getBoolean("delquyen"));
				list.add(catUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addquyen(CatUser catUer) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE cat_user SET addquyen = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, catUer.getAddquyen());
			pst.setInt(2, catUer.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int editquyen(CatUser catUer) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE cat_user SET editquyen = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, catUer.getEditquyen());
			pst.setInt(2, catUer.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int delquyen(CatUser catUer) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE cat_user SET delquyen = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, catUer.getDelquyen());
			pst.setInt(2, catUer.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
