package models;

import java.sql.Timestamp;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Perfume {
	private int id;
	private String perfumes;
	private String description;
	private String detail;
	private String brand;
	private String made;
	private String capacity;
	private String codePerfume;
	private int amount;
	private long money;
	private int evaluate;
	private Timestamp create_date;
	private int views;
	CatPerfume catPer;
	
	public Perfume(int id, String perfumes, String brand, int amount, long money, CatPerfume catPer) {
		super();
		this.id = id;
		this.perfumes = perfumes;
		this.brand = brand;
		this.amount = amount;
		this.money = money;
		this.catPer = catPer;
	}
	public Perfume(int id) {
		super();
		this.id = id;
	}
	
	/*
	 * public Perfume(String perfumes, String description, String detail, String
	 * brand, String made, String capacity, String codePerfume, int amount, long
	 * money, List<String> arpic, CatPerfume catPer) { super(); this.perfumes =
	 * perfumes; this.description = description; this.detail = detail; this.brand =
	 * brand; this.made = made; this.capacity = capacity; this.codePerfume =
	 * codePerfume; this.amount = amount; this.money = money; this.arpic = arpic;
	 * this.catPer = catPer; }
	 */
	/*
	 * public Perfume(int id, String perfumes, String description, String detail,
	 * String brand, String made, String capacity, String codePerfume, int amount,
	 * long money, int evaluate, Timestamp create_date, int views, CatPerfume
	 * catPer) { super(); this.id = id; this.perfumes = perfumes; this.description =
	 * description; this.detail = detail; this.brand = brand; this.made = made;
	 * this.capacity = capacity; this.codePerfume = codePerfume; this.amount =
	 * amount; this.money = money; this.evaluate = evaluate; this.create_date =
	 * create_date; this.views = views; this.catPer = catPer; }
	 */
	public Perfume(int id, String perfumes, String description, String detail, String brand, String made,
			String capacity, String codePerfume, int amount, long money, CatPerfume catPer) {
		super();
		this.id = id;
		this.perfumes = perfumes;
		this.description = description;
		this.detail = detail;
		this.brand = brand;
		this.made = made;
		this.capacity = capacity;
		this.codePerfume = codePerfume;
		this.amount = amount;
		this.money = money;
		this.catPer = catPer;
	}
	public Perfume(String perfumes, String description, String detail, String brand, String made, String capacity,
			String codePerfume, int amount, long money, CatPerfume catPer) {
		super();
		this.perfumes = perfumes;
		this.description = description;
		this.detail = detail;
		this.brand = brand;
		this.made = made;
		this.capacity = capacity;
		this.codePerfume = codePerfume;
		this.amount = amount;
		this.money = money;
		this.catPer = catPer;
	}
	
}
