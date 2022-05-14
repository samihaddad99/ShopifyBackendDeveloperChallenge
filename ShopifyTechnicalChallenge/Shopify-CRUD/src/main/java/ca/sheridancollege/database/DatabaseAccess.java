package ca.sheridancollege.database;

import java.beans.Statement;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Item;
import ca.sheridancollege.beans.Location;

@Repository
public class DatabaseAccess {

	private NamedParameterJdbcTemplate jdbc;
	
	public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public List<Item> getInventory() {
		
		String query = "SELECT * FROM inventory";
		
		BeanPropertyRowMapper<Item> mapper =
				new BeanPropertyRowMapper<>(Item.class);
		
		List<Item> inventory = jdbc.query(query, mapper); 
		
		return inventory;
		
	}
	
	public Item getItem(Long id) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM inventory WHERE id = :id";
		
		namedParameters.addValue("id", id);
		
		BeanPropertyRowMapper<Item> itemMapper =
				new BeanPropertyRowMapper<Item>(Item.class);
		
		List<Item> inventory = jdbc.query(query, namedParameters, itemMapper);
		
		if(inventory.isEmpty()) {
			return null;
		} else {
			return inventory.get(0);
		}
	}
	
	public void addItem(Item item) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO inventory (name, price, location) VALUES"
				+ " (:name, :price, :location)";
		
		parameters
			.addValue("name", item.getName())
			.addValue("price", item.getPrice())
			.addValue("location", item.getLocation());
		
		jdbc.update(query, parameters);
		
	}
	
	public void deleteItem(Long id) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "DELETE FROM inventory WHERE id = :id"; 
		
		parameters.addValue("id", id);

		jdbc.update(query, parameters);
		
	}
	
	public void updateItem(Item item) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "UPDATE inventory "
				+ "SET name=:name, price=:price, location=:location WHERE id=:id";
		
		parameters
			.addValue("name", item.getName())
			.addValue("price", item.getPrice())
			.addValue("id", item.getId())
			.addValue("location", item.getLocation());
		
		
		jdbc.update(query, parameters);

	}
	
	public List<Location> getLocations() {
		
		String query = "SELECT * FROM locations";
		
		BeanPropertyRowMapper<Location> itemMapper =
				new BeanPropertyRowMapper<Location>(Location.class);
		
		List<Location> locations = jdbc.query(query, itemMapper);
		
		if(locations.isEmpty()) {
			return null;
		} else {
			
			return locations;
		}
		
	}
	
	public Location getLocation(Long id) {
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		List<Location> locations = getLocations();
		
		String query = "SELECT * FROM locations WHERE id = :id";
		
		namedParameters.addValue("id", id);
		
		BeanPropertyRowMapper<Location> itemMapper =
				new BeanPropertyRowMapper<Location>(Location.class);
		
		List<Location> inventory = jdbc.query(query, namedParameters, itemMapper);
		
		if(locations.isEmpty()) {
			return null;
		} else {
			return locations.get(id.intValue());
		}
		
	}
	
	public void updateLocation(Location location) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "UPDATE locations "
				+ "SET name=:name WHERE id=:id";
		
		parameters
			.addValue("name", location.getName())
			.addValue("id", location.getId());
		
		jdbc.update(query, parameters);
	}

	
	public void updateLocations(Location location) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "UPDATE locations "
				+ "SET name=:name WHERE id=:id";
		
		parameters
			.addValue("name", location.getName())
			.addValue("id", location.getId());
		
		jdbc.update(query, parameters);

	}
	
	public void addLocation(Location location) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO locations (name) VALUES"
				+ " (:name)";
		
		parameters
			.addValue("name", location.getName());
		
		jdbc.update(query, parameters);
		
	}

}
