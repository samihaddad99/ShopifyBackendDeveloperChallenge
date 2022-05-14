package ca.sheridancollege.beans;

import lombok.Data;

@Data
public class Item {

	private Long id;
	private String name;
	private double price;
	private String location;
	
}
