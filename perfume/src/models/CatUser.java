package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CatUser {
	private int id;
	private String catUser;
	private Boolean addquyen;
	private Boolean editquyen;
	private Boolean delquyen;
	
	public CatUser(String catUser) {
		super();
		this.catUser = catUser;
	}

	public CatUser(int id) {
		super();
		this.id = id;
	}

	public CatUser(int id, String catUser) {
		super();
		this.id = id;
		this.catUser = catUser;
	}

	public CatUser(int id, Boolean addquyen, Boolean editquyen, Boolean delquyen) {
		super();
		this.id = id;
		this.addquyen = addquyen;
		this.editquyen = editquyen;
		this.delquyen = delquyen;
	}
	
}
