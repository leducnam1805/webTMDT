package daos;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CatPerfume;
import models.Perfume;
import models.Picture;
import util.DBConnectionUtil;

public class PerfumeDao extends AbstractDAO {

	public List<Perfume> findAll() {
		List<Perfume> list = new ArrayList<Perfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT perfumes.*,cat.catperfume AS catPF,cat.id AS catID" + " FROM perfumes"
				+ " INNER JOIN cat_perfume AS cat ON perfumes.cat_perfume = cat.id" + " ORDER BY perfumes.id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Perfume perfume = new Perfume(rs.getInt("id"), rs.getString("name_per"), rs.getString("description"),
						rs.getString("detail"), rs.getString("brand"), rs.getString("made"), rs.getString("capacity"),
						rs.getString("code_per"), rs.getInt("amount"), rs.getLong("money"), rs.getInt("evaluate"),
						rs.getTimestamp("create_date"), rs.getInt("view"),
						new CatPerfume(rs.getInt("catID"), rs.getString("catPF")));
				list.add(perfume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int add(Perfume perfume) {
		int result = 0;
		int idValue = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO perfumes(name_per,description,detail,brand,"
				+ "made,capacity,code_per,amount,money,cat_perfume)" + " VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(sql, st.RETURN_GENERATED_KEYS);
			pst.setString(1, perfume.getPerfumes());
			pst.setString(2, perfume.getDescription());
			pst.setString(3, perfume.getDetail());
			pst.setString(4, perfume.getBrand());
			pst.setString(5, perfume.getMade());
			pst.setString(6, perfume.getCapacity());
			pst.setString(7, perfume.getCodePerfume());
			pst.setInt(8, perfume.getAmount());
			pst.setLong(9, perfume.getMoney());
			pst.setInt(10, perfume.getCatPer().getId());
			result = pst.executeUpdate();

			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				idValue = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idValue;
	}

	public Perfume viewsID(int idPF) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT perfumes.*,cat.catperfume AS catPF,cat.id AS catID,pic_per.*" + " FROM perfumes"
				+ " INNER JOIN cat_perfume AS cat ON perfumes.cat_perfume = cat.id"
				+ " INNER JOIN pic_per ON perfumes.id = pic_per.per_id" + " WHERE perfumes.id = ?";
		Perfume product = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idPF);
			rs = pst.executeQuery();
			if (rs.next()) {
				product = new Perfume(rs.getInt("id"), rs.getString("name_per"), rs.getString("description"),
						rs.getString("detail"), rs.getString("brand"), rs.getString("made"), rs.getString("capacity"),
						rs.getString("code_per"), rs.getInt("amount"), rs.getLong("money"), rs.getInt("evaluate"),
						rs.getTimestamp("create_date"), rs.getInt("view"),
						new CatPerfume(rs.getInt("catID"), rs.getString("catPF")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public int edit(Perfume perfume) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE perfumes SET" + " name_per = ?," + " description = ?," + " detail = ?," + " brand = ?,"
				+ " made = ?," + " capacity = ?," + " code_per = ?," + " amount = ?," + " money = ?,"
				+ " cat_perfume = ?" + " WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, perfume.getPerfumes());
			pst.setString(2, perfume.getDescription());
			pst.setString(3, perfume.getDetail());
			pst.setString(4, perfume.getBrand());
			pst.setString(5, perfume.getMade());
			pst.setString(6, perfume.getCapacity());
			pst.setString(7, perfume.getCodePerfume());
			pst.setInt(8, perfume.getAmount());
			pst.setLong(9, perfume.getMoney());
			pst.setInt(10, perfume.getCatPer().getId());
			pst.setInt(11, perfume.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int del(int idPF) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM perfumes WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idPF);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Perfume> findAllEvaluate() {
		List<Perfume> listEVA = new ArrayList<Perfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT perfumes.*,cat.catperfume AS catPF,cat.id AS catID" + " FROM perfumes"
				+ " INNER JOIN cat_perfume AS cat ON perfumes.cat_perfume = cat.id" + " ORDER BY perfumes.id DESC"
				+ " LIMIT 4";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Perfume perfume = new Perfume(rs.getInt("id"), rs.getString("name_per"), rs.getString("description"),
						rs.getString("detail"), rs.getString("brand"), rs.getString("made"), rs.getString("capacity"),
						rs.getString("code_per"), rs.getInt("amount"), rs.getLong("money"), rs.getInt("evaluate"),
						rs.getTimestamp("create_date"), rs.getInt("view"),
						new CatPerfume(rs.getInt("catID"), rs.getString("catPF")));
				listEVA.add(perfume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEVA;
	}

	public List<Perfume> findAllViews() {
		List<Perfume> listVIE = new ArrayList<Perfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT perfumes.*,cat.catperfume AS catPF,cat.id AS catID" 
				+ " FROM perfumes"
				+ " INNER JOIN cat_perfume AS cat ON perfumes.cat_perfume = cat.id"
				+ " ORDER BY perfumes.view DESC LIMIT 1";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Perfume perfume = new Perfume(rs.getInt("id"),
						rs.getString("name_per"),
						rs.getString("description"),
						rs.getString("detail"),
						rs.getString("brand"),
						rs.getString("made"),
						rs.getString("capacity"),
						rs.getString("code_per"),
						rs.getInt("amount"),
						rs.getLong("money"),
						rs.getInt("evaluate"),
						rs.getTimestamp("create_date"),
						rs.getInt("view"),
						new CatPerfume(rs.getInt("catID"), rs.getString("catPF")));
				listVIE.add(perfume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listVIE;
	}

	public int getCount(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE perfumes SET view = view + 1 WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Perfume viewsCatID(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT perfumes.*,cat.catperfume AS catPF,cat.id AS catID" 
				+ " FROM perfumes"
				+ " INNER JOIN cat_perfume AS cat ON perfumes.cat_perfume = cat.id"
				+ " WHERE perfumes.cat_perfume = ?";
		Perfume product = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				product = new Perfume(rs.getInt("id"),
						rs.getString("name_per"),
						rs.getString("description"),
						rs.getString("detail"),
						rs.getString("brand"),
						rs.getString("made"),
						rs.getString("capacity"),
						rs.getString("code_per"),
						rs.getInt("amount")
						, rs.getLong("money"),
						rs.getInt("evaluate"),
						rs.getTimestamp("create_date"),
						rs.getInt("view"),
						new CatPerfume(rs.getInt("catID"), rs.getString("catPF")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public List<Perfume> FindCat() {
		con = DBConnectionUtil.getConnection();
		List<Perfume> perCatList = new ArrayList<Perfume>();
		String sql = "SELECT perfumes.*,cat_perfume.* FROM perfumes"
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id"
				+ " WHERE perfumes.cat_perfume < 3 limit 3";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Perfume perCat = new Perfume(rs.getInt("id"),
						rs.getString("name_per"),
						rs.getString("description"),
						rs.getString("detail"),
						rs.getString("brand"),
						rs.getString("made"),
						rs.getString("capacity"),
						rs.getString("code_per"),
						rs.getInt("amount"),
						rs.getLong("money"),
						rs.getInt("evaluate"),
						rs.getTimestamp("create_date"),
						rs.getInt("view"),
						new CatPerfume(rs.getInt("cat_perfume"), rs.getString("catperfume")));
				perCatList.add(perCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perCatList;
	}

	public List<Perfume> findCatID(int id) {
		List<Perfume> findCatID = new ArrayList<Perfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT perfumes.*,cat_perfume.*"
		+ " FROM `perfumes`" 
		+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id" 
		+ " WHERE cat_perfume = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Perfume perCat = new Perfume(rs.getInt("id"),
						rs.getString("name_per"),
						rs.getString("description"),
						rs.getString("detail"),
						rs.getString("brand"),
						rs.getString("made"),
						rs.getString("capacity"),
						rs.getString("code_per"),
						rs.getInt("amount"),
						rs.getLong("money"),
						rs.getInt("evaluate"),
						rs.getTimestamp("create_date"),
						rs.getInt("view"),
						new CatPerfume(rs.getInt("cat_perfume"), rs.getString("catperfume")));
				findCatID.add(perCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findCatID;
	}

	public List<Perfume> findNewID(int id) {
		List<Perfume> findNewID = new ArrayList<Perfume>();
		String sql = "SELECT perfumes.*,cat_perfume.*"
				+ " FROM `perfumes`" 
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id" 
				+ " WHERE cat_perfume = ?"
				+ " ORDER BY perfumes.id DESC LIMIT 12";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Perfume perCat = new Perfume(rs.getInt("id"),
						rs.getString("name_per"),
						rs.getString("description"),
						rs.getString("detail"),
						rs.getString("brand"),
						rs.getString("made"),
						rs.getString("capacity"),
						rs.getString("code_per"),
						rs.getInt("amount"),
						rs.getLong("money"),
						rs.getInt("evaluate"),
						rs.getTimestamp("create_date"),
						rs.getInt("view"),
						new CatPerfume(rs.getInt("cat_perfume"), rs.getString("catperfume")));
				findNewID.add(perCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findNewID;
	}

	public List<Perfume> findAllView(int id) {
		List<Perfume> findAllView = new ArrayList<Perfume>();
		String sql = "SELECT perfumes.*,cat_perfume.*"
				+ " FROM `perfumes`" 
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id" 
				+ " WHERE cat_perfume = ?"
				+ " ORDER BY perfumes.view DESC LIMIT 12";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Perfume perCat = new Perfume(rs.getInt("id"),
						rs.getString("name_per"),
						rs.getString("description"),
						rs.getString("detail"),
						rs.getString("brand"),
						rs.getString("made"),
						rs.getString("capacity"),
						rs.getString("code_per"),
						rs.getInt("amount"),
						rs.getLong("money"),
						rs.getInt("evaluate"),
						rs.getTimestamp("create_date"),
						rs.getInt("view"),
						new CatPerfume(rs.getInt("cat_perfume"), rs.getString("catperfume")));
				findAllView.add(perCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findAllView;
	}

	public List<Perfume> ortherList(int catPer) {
		List<Perfume> ortherList = new ArrayList<Perfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT perfumes.*,cat_perfume.*"
				+ " FROM `perfumes`" 
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id" 
				+ " WHERE cat_perfume = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, catPer);
			rs = pst.executeQuery();
			while(rs.next()) {
				Perfume perCat = new Perfume(rs.getInt("id"),
						rs.getString("name_per"),
						rs.getString("description"),
						rs.getString("detail"),
						rs.getString("brand"),
						rs.getString("made"),
						rs.getString("capacity"),
						rs.getString("code_per"),
						rs.getInt("amount"),
						rs.getLong("money"),
						rs.getInt("evaluate"),
						rs.getTimestamp("create_date"),
						rs.getInt("view"),
						new CatPerfume(rs.getInt("cat_perfume"), rs.getString("catperfume")));
				ortherList.add(perCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ortherList;
	}

	public List<Perfume> findProNew() {
		List<Perfume> findProNew = new ArrayList<Perfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT perfumes.*,cat_perfume.*"
				+ " FROM `perfumes`" 
				+ " INNER JOIN cat_perfume ON perfumes.cat_perfume = cat_perfume.id" 
				+ " ORDER BY perfumes.id DESC LIMIT 1";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Perfume perCat = new Perfume(rs.getInt("id"),
						rs.getString("name_per"),
						rs.getString("description"),
						rs.getString("detail"),
						rs.getString("brand"),
						rs.getString("made"),
						rs.getString("capacity"),
						rs.getString("code_per"),
						rs.getInt("amount"),
						rs.getLong("money"),
						rs.getInt("evaluate"),
						rs.getTimestamp("create_date"),
						rs.getInt("view"),
						new CatPerfume(rs.getInt("cat_perfume"), rs.getString("catperfume")));
				findProNew.add(perCat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findProNew;
	}

	public int delCatPF(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM perfumes WHERE cat_perfume = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countID() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT count(*) AS count FROM `perfumes`";
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
