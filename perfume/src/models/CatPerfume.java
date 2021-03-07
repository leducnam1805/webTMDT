package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CatPerfume {
	private int id;
	private String catPerfume;
	private int parrent_id;
	
	
	public CatPerfume(String catPerfume, int parrent_id) {
		super();
		this.catPerfume = catPerfume;
		this.parrent_id = parrent_id;
	}

	public CatPerfume(String catPerfume) {
		super();
		this.catPerfume = catPerfume;
	}

	public CatPerfume(int id, String catPerfume) {
		super();
		this.id = id;
		this.catPerfume = catPerfume;
	}

	public CatPerfume(int id) {
		super();
		this.id = id;
	}
	
}
