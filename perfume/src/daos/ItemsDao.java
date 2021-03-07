package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CatPerfume;
import models.CatUser;
import models.Item;
import models.Order;
import models.Perfume;
import models.Picture;
import models.User;
import util.DBConnectionUtil;

public class ItemsDao extends AbstractDAO{

	public int add(Item items) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO item(quantity,price,per_id,order_id) VALUES(?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, items.getQuantity());
			pst.setLong(2, items.getPrice());
			pst.setInt(3, items.getProduct().getId());
			pst.setInt(4, items.getOrder().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Item> findAll() {
		List<Item> itemList = new ArrayList<Item>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT item.*,perfumes.*,`order`.*,user.*,cat_perfume.*,cat_user.*"
				+ " FROM item"
				+ " INNER JOIN `order` ON item.order_id = `order`.id"
				+ " INNER JOIN user ON `order`.`user_id` = user.id"
				+ " INNER JOIN cat_user ON user.cat_user = cat_user.id"
				+ " INNER JOIN perfumes ON item.per_id = perfumes.id"
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id"
				+ " ORDER BY `order`.status,`order`.id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Item item = new Item(rs.getInt("id"),
						new Perfume(rs.getInt("per_id"),rs.getString("name_per"), 
								rs.getString("description"), rs.getString("detail"),
								rs.getString("brand"), rs.getString("made"), rs.getString("capacity"),
								rs.getString("code_per"), rs.getInt("amount"), rs.getLong("money"),
								rs.getInt("evaluate"), rs.getTimestamp("create_date"), rs.getInt("view"),
								new CatPerfume(rs.getInt("cat_perfume"),rs.getString("catperfume"))),
						rs.getInt("quantity"), rs.getLong("price"),
						new Order(rs.getInt("order_id"),
								new User(rs.getInt("user_id"), rs.getString("username"),rs.getString("fullname"),
										rs.getString("password"), rs.getString("money"), 
										new CatUser(rs.getInt("cat_user"), rs.getString("catuser"))),
						rs.getBoolean("status"), rs.getString("phone"), rs.getString("email"),
						rs.getString("adress"), rs.getString("note")));
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}

	public Item viewOrder(int idOrder) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT item.*,perfumes.*,`order`.*,user.*,cat_perfume.*,cat_user.*"
				+ " FROM item"
				+ " INNER JOIN `order` ON item.order_id = `order`.id"
				+ " INNER JOIN user ON `order`.`user_id` = user.id"
				+ " INNER JOIN cat_user ON user.cat_user = cat_user.id"
				+ " INNER JOIN perfumes ON item.per_id = perfumes.id"
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id"
				+ " where item.id = ?";
		Item objItem = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idOrder);
			rs = pst.executeQuery();
			if(rs.next()) {
				objItem = new Item(rs.getInt("id"),
						new Perfume(rs.getInt("per_id"),rs.getString("name_per"), 
								rs.getString("description"), rs.getString("detail"),
								rs.getString("brand"), rs.getString("made"), rs.getString("capacity"),
								rs.getString("code_per"), rs.getInt("amount"), rs.getLong("money"),
								rs.getInt("evaluate"), rs.getTimestamp("create_date"), rs.getInt("view"),
								new CatPerfume(rs.getInt("cat_perfume"),rs.getString("catperfume"))),
						rs.getInt("quantity"), rs.getLong("price"),
						new Order(rs.getInt("order_id"),
								new User(rs.getInt("user_id"), rs.getString("username"),rs.getString("fullname"),
										rs.getString("password"), rs.getString("money"), 
										new CatUser(rs.getInt("cat_user"), rs.getString("catuser"))),
						rs.getBoolean("status"), rs.getString("phone"), rs.getString("email"),
						rs.getString("adress"), rs.getString("note")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objItem;
	}

	public List<Item> search(String namePro) {
		List<Item> searchPro = new ArrayList<Item>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT item.*,perfumes.*,`order`.*,user.*,cat_perfume.*,cat_user.*"
				+ " FROM item"
				+ " INNER JOIN `order` ON item.order_id = `order`.id"
				+ " INNER JOIN user ON `order`.`user_id` = user.id"
				+ " INNER JOIN cat_user ON user.cat_user = cat_user.id"
				+ " INNER JOIN perfumes ON item.per_id = perfumes.id"
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id"
				+ " where perfumes.name_per LIKE ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, "%"+namePro+"%");
			rs = pst.executeQuery();
			while(rs.next()) {
				Item item = new Item(rs.getInt("id"),
						new Perfume(rs.getInt("per_id"),rs.getString("name_per"), 
								rs.getString("description"), rs.getString("detail"),
								rs.getString("brand"), rs.getString("made"), rs.getString("capacity"),
								rs.getString("code_per"), rs.getInt("amount"), rs.getLong("money"),
								rs.getInt("evaluate"), rs.getTimestamp("create_date"), rs.getInt("view"),
								new CatPerfume(rs.getInt("cat_perfume"),rs.getString("catperfume"))),
						rs.getInt("quantity"), rs.getLong("price"),
						new Order(rs.getInt("order_id"),
								new User(rs.getInt("user_id"), rs.getString("username"),rs.getString("fullname"),
										rs.getString("password"), rs.getString("money"), 
										new CatUser(rs.getInt("cat_user"), rs.getString("catuser"))),
						rs.getBoolean("status"), rs.getString("phone"), rs.getString("email"),
						rs.getString("adress"), rs.getString("note")));
				searchPro.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return searchPro;
	}

	public List<Item> findAllBanking() {
		List<Item> itemList = new ArrayList<Item>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT item.*,perfumes.*,`order`.*,user.*,cat_perfume.*,cat_user.*"
				+ " FROM item"
				+ " INNER JOIN `order` ON item.order_id = `order`.id"
				+ " INNER JOIN user ON `order`.`user_id` = user.id"
				+ " INNER JOIN cat_user ON user.cat_user = cat_user.id"
				+ " INNER JOIN perfumes ON item.per_id = perfumes.id"
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id"
				+ " WHERE `order`.status = true"
				+ " ORDER BY `order`.status,`order`.id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Item item = new Item(rs.getInt("id"),
						new Perfume(rs.getInt("per_id"),rs.getString("name_per"), 
								rs.getString("description"), rs.getString("detail"),
								rs.getString("brand"), rs.getString("made"), rs.getString("capacity"),
								rs.getString("code_per"), rs.getInt("amount"), rs.getLong("money"),
								rs.getInt("evaluate"), rs.getTimestamp("create_date"), rs.getInt("view"),
								new CatPerfume(rs.getInt("cat_perfume"),rs.getString("catperfume"))),
						rs.getInt("quantity"), rs.getLong("price"),
						new Order(rs.getInt("order_id"),
								new User(rs.getInt("user_id"), rs.getString("username"),rs.getString("fullname"),
										rs.getString("password"), rs.getString("money"), 
										new CatUser(rs.getInt("cat_user"), rs.getString("catuser"))),
						rs.getBoolean("status"), rs.getString("phone"), rs.getString("email"),
						rs.getString("adress"), rs.getString("note")));
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
	
}
