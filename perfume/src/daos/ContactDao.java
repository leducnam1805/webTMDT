package daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CatUser;
import models.Contact;
import models.User;
import util.DBConnectionUtil;

public class ContactDao extends AbstractDAO {
	
	public List<Contact> findAll(){
		List<Contact> list = new ArrayList<Contact>();
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT * FROM contact"
				+ " order by id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Contact contact = new Contact(rs.getInt("id"),
						rs.getString("name"), 
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("message"));
				list.add(contact);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int sendData(Contact contact) {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "INSERT INTO contact(name,email,phone,message) VALUES(?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getPhone());
			pst.setString(4, contact.getMessage());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int countID() {
		int result = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "SELECT count(*) AS count FROM `contact`";
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
