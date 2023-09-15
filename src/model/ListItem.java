package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jonathan Argueta-Herrera - jiarguetaherrera
 * CIS175 Fall 2023
 * Sep, 14
 */

@Entity	
@Table(name="snacks")
public class ListItem {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="SNACK")
	private String snack;
	@Column(name="PRICE")
	private int price;
	
	
	public ListItem() {
		super();
	}


	public ListItem(String snack, int price) {
		super();
		this.snack = snack;
		this.price = price;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSnack() {
		return snack;
	}


	public void setSnack(String snack) {
		this.snack = snack;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	public String returnSnacks() {
		return this.snack + ": $" + this.price;
	}

}
