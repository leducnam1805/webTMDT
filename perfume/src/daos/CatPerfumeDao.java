package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CatPerfume;
import util.DBConnectionUtil;

public class CatPerfumeDao extends AbstractDAO{
	
	public List<CatPerfume> findAllBrand(){
		List<CatPerfume> list = new ArrayList<CatPerfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_perfume"
				+ " order by id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				CatPerfume catPerfume = new CatPerfume(rs.getInt("id"),
						rs.getString("catperfume"),
						rs.getInt("parrent_id"));
				list.add(catPerfume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<CatPerfume> findAllBrandN(){
		List<CatPerfume> list = new ArrayList<CatPerfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_perfume";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				CatPerfume catPerfume = new CatPerfume(rs.getInt("id"),
						rs.getString("catperfume"),
						rs.getInt("parrent_id"));
				list.add(catPerfume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * public List<CatPerfume> findAll(){ List<CatPerfume> list = new
	 * ArrayList<CatPerfume>(); con = DBConnectionUtil.getConnection(); String sql =
	 * "SELECT * FROM cat_perfume WHERE parrent_id != 0" + " order by id DESC"; try
	 * { st = con.createStatement(); rs = st.executeQuery(sql); while(rs.next()) {
	 * CatPerfume catPerfume = new CatPerfume(rs.getInt("id"),
	 * rs.getString("catperfume"), rs.getInt("parrent_id")); list.add(catPerfume); }
	 * } catch (SQLException e) { e.printStackTrace(); } return list; }
	 */
	
	public List<CatPerfume> findAllID() {
		List<CatPerfume> list = new ArrayList<CatPerfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_perfume WHERE parrent_id = 0"
				+ " order by id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				CatPerfume catPerfume = new CatPerfume(rs.getInt("id"),
						rs.getString("catperfume"),
						rs.getInt("parrent_id"));
				list.add(catPerfume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	public CatPerfume test(String name) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_perfume where catperfume = ?";
		CatPerfume test = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if(rs.next()) {
				test = new CatPerfume(rs.getInt("id"), rs.getString("catperfume"),rs.getInt("parrent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return test;
	}

	public int add(CatPerfume catPF) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO cat_perfume(catperfume,parrent_id) VALUES(?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, catPF.getCatPerfume());
			pst.setInt(2, catPF.getParrent_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public CatPerfume viewID(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_perfume WHERE id = ?";
		CatPerfume objCatPF = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				objCatPF = new CatPerfume(rs.getInt("id"), rs.getString("catperfume"),rs.getInt("parrent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objCatPF;
	}

	public CatPerfume testID(int id,String name) {
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_perfume where catperfume = ? AND id != ?";
		CatPerfume test = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				test = new CatPerfume(rs.getInt("id"), rs.getString("catperfume"),rs.getInt("parrent_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return test;
	}

	public int edit(CatPerfume catPF) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "UPDATE cat_perfume SET catperfume = ?,parrent_id = ? WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, catPF.getCatPerfume());
			pst.setInt(2, catPF.getParrent_id());
			pst.setInt(3, catPF.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int del(int id) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "DELETE FROM cat_perfume WHERE id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<CatPerfume> getCatParent(int parent) {
		List<CatPerfume> catPFDao = new ArrayList<CatPerfume>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM cat_perfume WHERE parrent_id = ? ORDER BY id DESC";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, parent);
			rs = pst.executeQuery();
			while(rs.next()) {
				CatPerfume objCatPF = new CatPerfume(rs.getInt("id"),
						rs.getString("catperfume"),
						rs.getInt("parrent_id"));
				catPFDao.add(objCatPF);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return catPFDao;
	}

}