package ca.sheridancollege.beans;

import lombok.Data;

@Data
public class Location {

	private Long id;
	private String name;

	@Override
	public String toString() {
		return name;
	}
}
