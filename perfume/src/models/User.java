package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
	private int id;
	private String username;
	private String fullname;
	private String password;
	private String money;
	CatUser catUser;
	
	public User(String username, String fullname, String password, String money, CatUser catUser) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.money = money;
		this.catUser = catUser;
	}

	public User(String username) {
		super();
		this.username = username;
	}

	public User(int id, String fullname, String password, CatUser catUser) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.password = password;
		this.catUser = catUser;
	}

	public User(int id, String fullname, CatUser catUser) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.catUser = catUser;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String fullname, String password) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
	}

	public User(int id) {
		super();
		this.id = id;
	}

}
