package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private int id;
	private Perfume product;
	private int quantity;
	private long price;
	private Order order;
	
	public Item(int id) {
		super();
		this.id = id;
	}

	public Item(Perfume product, int quantity, long price, Order order) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.order = order;
	}
	
	
}
