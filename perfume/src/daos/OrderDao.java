package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CatUser;
import models.Item;
import models.Order;
import models.Perfume;
import models.User;
import util.DBConnectionUtil;

public class OrderDao extends AbstractDAO{

	public int order(Order order) {
		int result = 0;
		int idValue = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO `order`(user_id,phone,email,adress,note) VALUES(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql,st.RETURN_GENERATED_KEYS);
			pst.setInt(1, order.getCustomer().getId());
			pst.setString(2, order.getPhone());
			pst.setString(3, order.getEmail());
			pst.setString(4, order.getAdress());
			pst.setString(5, order.getNote());
			result = pst.executeUpdate();
			
			rs = pst.getGeneratedKeys();
			if(rs.next()) {
				idValue = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idValue;
	}

	public List<Order> findAll() {
		con = DBConnectionUtil.getConnection();
		List<Order> orderList = new ArrayList<Order>();
		String sql = "SELECT `order`.*,user.*"
				+ " FROM `order`"
				+ " INNER JOIN user ON `order`.`user_id` = user.id"
				+ " ORDER BY `order`.`id` DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Order order = new Order(rs.getInt("id"),
						new User(rs.getString("username"), rs.getString("fullname"),
								rs.getString("password"), rs.getString("money"),
								new CatUser(rs.getInt("cat_user"))),
						rs.getBoolean("status"), rs.getString("phone"), rs.getString("email"),
						rs.getString("adress"),rs.getString("note"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public int addStatus(Order order) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE `order` SET status = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, order.getStatus());
			pst.setInt(2, order.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countID() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT count(*) AS count FROM `order`";
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
