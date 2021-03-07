package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CatUser;
import models.User;
import util.DBConnectionUtil;
import util.DefineUtil;

public class UserDao extends AbstractDAO {
	
	public List<User> findAll(){
		List<User> list = new ArrayList<User>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT user.*,cat_user.id AS catID,cat_user.catuser AS catuser FROM user"
				+ " INNER JOIN cat_user"
				+ " ON user.cat_user = cat_user.id"
				+ " order by id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				User user = new User(rs.getInt("id"),
						rs.getString("username"), 
						rs.getString("fullname"), 
						rs.getString("password"), 
						rs.getString("money"), 
						new CatUser(rs.getInt("cat_user"),
							rs.getString("catuser")));
				list.add(user);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public User test(String username) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT username FROM user WHERE username = ?";
		User objUser = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				objUser = new User(rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objUser;
	}
	
	public int add(User user) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO user(username,fullname,password,money,cat_user) VALUES(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getFullname());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getMoney());
			pst.setInt(5, user.getCatUser().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User viewsID(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT user.*,cu.id AS cuid,cu.catuser AS cucat FROM user"
				+ " INNER JOIN cat_user AS cu"
				+ " ON user.cat_user = cu.id"
				+ " WHERE user.id = ?";
		User objUser = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				objUser = new User(rs.getInt("id"),
						rs.getString("username"),
						rs.getString("fullname"),
						rs.getString("password"),
						rs.getString("money"),
						new CatUser(rs.getInt("cat_user"), rs.getString("cucat")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objUser;
	}

	public int edit(User user) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE user SET fullname = ?, password = ?, cat_user = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getFullname());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getCatUser().getId());
			pst.setInt(4, user.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int del(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM user WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<User> searchIDCat(int catUser) {
		List<User> searchIDCat = new ArrayList<User>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT user.*, catUser.id AS catID,catUser.catuser AS catUS FROM user"
				+ " INNER JOIN cat_user AS catUser ON user.cat_user = catUser.id"
				+ " WHERE user.cat_user = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, catUser);
			rs = pst.executeQuery();
			while(rs.next()) {
				User objUser = new User(rs.getInt("id"),
						rs.getString("username"),
						rs.getString("fullname"),
					rs.getString("password"),
					rs.getString("money"),
					new CatUser(rs.getInt("cat_user"), rs.getString("catUS"))); 
				searchIDCat.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchIDCat;
	}

	public List<User> searchName(String nameUser) {
		List<User> searchName = new ArrayList<User>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT user.*, catUser.id AS catID,catUser.catuser AS catUS FROM user"
				+ " INNER JOIN cat_user AS catUser ON user.cat_user = catUser.id"
				+ " WHERE username LIKE ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, "%"+nameUser+"%");
			rs = pst.executeQuery();
			while(rs.next()) {
				User objUser = new User(rs.getInt("id"),
						rs.getString("username"),
						rs.getString("fullname"),
					rs.getString("password"),
					rs.getString("money"),
					new CatUser(rs.getInt("cat_user"), rs.getString("catUS"))); 
				searchName.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchName;
	}

	public List<User> searchIDName(int catUser,String nameUser) {
		List<User> searchIDName = new ArrayList<User>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT user.*, catUser.id AS catID,catUser.catuser AS catUS FROM user"
				+ " INNER JOIN cat_user AS catUser ON user.cat_user = catUser.id"
				+ " WHERE cat_user = ? AND username LIKE ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, catUser);
			pst.setString(2, "%"+nameUser+"%");
			rs = pst.executeQuery();
			while(rs.next()) {
				User objUser = new User(rs.getInt("id"),
						rs.getString("username"),
						rs.getString("fullname"),
					rs.getString("password"),
					rs.getString("money"),
					new CatUser(rs.getInt("cat_user"), rs.getString("catUS"))); 
				searchIDName.add(objUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchIDName;
	}

	public int getCount() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT COUNT(id) AS count FROM user";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<User> findAllAdmin(int offsetAdmin) {
		List<User> itemsAdmin = new ArrayList<User>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT user.*,cat_user.id AS catID,cat_user.catuser AS catuser FROM user"
				+ " INNER JOIN cat_user"
				+ " ON user.cat_user = cat_user.id"
				+ " order by id DESC LIMIT ?,?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, offsetAdmin);
			pst.setInt(2, DefineUtil.NUMBER_PER_PAGE_ADMIN);
			rs = pst.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("id"),
						rs.getString("username"), 
						rs.getString("fullname"), 
						rs.getString("password"), 
						rs.getString("money"), 
						new CatUser(rs.getInt("cat_user"),
							rs.getString("catuser")));
				itemsAdmin.add(user);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemsAdmin;
	}

	public int signup(User user) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO user(username,fullname,password) VALUES(?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getFullname());
			pst.setString(3, user.getPassword());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User login(String username, String password) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT username,password FROM user WHERE username = ? AND password = ?";
		User onjUser = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				onjUser = new User(rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return onjUser;
	}

	public User viewsALL(String username) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT user.*,cat_user.id AS catID,cat_user.catuser AS catUSER"
				+ " FROM user"
				+ " INNER JOIN cat_user ON user.cat_user = cat_user.id"
				+ " WHERE username = ?";
		User objUser = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				objUser = new User(rs.getInt("id"),
						rs.getString("username"),
						rs.getString("fullname"),
						rs.getString("password"),
						rs.getString("money"),
						new CatUser(rs.getInt("catID"), rs.getString("catUSER")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objUser;
	}

	public int countID() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT count(*) AS count FROM `user`";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
