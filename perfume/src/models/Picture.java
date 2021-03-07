package models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
	private int id;
	private String picture;
	Perfume product;
	
	public Picture(int id) {
		super();
		this.id = id;
	}

	public Picture(String picture, Perfume product) {
		super();
		this.picture = picture;
		this.product = product;
	}

	public Picture(Perfume product) {
		super();
		this.product = product;
	}

	public Picture(int id, String picture) {
		super();
		this.id = id;
		this.picture = picture;
	}

	public Picture(String picture) {
		super();
		this.picture = picture;
	}

}
