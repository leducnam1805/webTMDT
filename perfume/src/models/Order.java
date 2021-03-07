package models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order implements Serializable {
	private int id;
	private User customer;
	private List<Item> item;
	private Boolean status;
	private String phone;
	private String email;
	private String adress;
	private String note;
	
	public Order(User customer, String phone, String email, String adress, String note) {
		super();
		this.customer = customer;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
		this.note = note;
	}

	public Order(int id) {
		super();
		this.id = id;
	}

	public Order(int id, User customer, Boolean status, String phone, String email, String adress, String note) {
		super();
		this.id = id;
		this.customer = customer;
		this.status = status;
		this.phone = phone;
		this.email = email;
		this.adress = adress;
		this.note = note;
	}

	public Order(int id, Boolean status) {
		super();
		this.id = id;
		this.status = status;
	}
	
}
