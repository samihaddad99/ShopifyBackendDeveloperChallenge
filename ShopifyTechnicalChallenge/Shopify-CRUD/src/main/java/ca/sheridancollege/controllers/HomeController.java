package ca.sheridancollege.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.beans.Item;
import ca.sheridancollege.beans.Location;
import ca.sheridancollege.database.DatabaseAccess;

@Controller
public class HomeController {

	private DatabaseAccess database;

	public HomeController(DatabaseAccess database) {
		
		this.database = database;
		
	}
	
	@GetMapping("/")
	public String goHome(Model model) {
		
		List<Item> inventory = database.getInventory();
		
		model.addAttribute("inventory", inventory);
		
		return "index";
		
	}
	
	@GetMapping("/addItem")
	public String goToAdd(Model model) {
		
		List<Location> locations = database.getLocations();
		
		model.addAttribute("item", new Item())
			.addAttribute("locations", locations);
		
		return "add_item";
		
	}
	
	@PostMapping("/addItem")
	public String addItem(@ModelAttribute Item item, Model model) {
		
		List<Location> locations = database.getLocations();
		
		database.addItem(item);
		
		model.addAttribute("locations", locations);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable Long id) {
		
		database.deleteItem(id);
		
		return "redirect:/?message=deleted";
		
	}
	
	@GetMapping("/editItem/{id}")
	public String editItem(@PathVariable Long id, Model model) {
		
		Item item = database.getItem(id);
		
		List<Location> locations = database.getLocations();
		
		model.addAttribute("item", item)
			.addAttribute("locations", locations);
		
		return "edit_item";
		
	}
	
	@PostMapping("/updateItem")
	public String updateItem(@ModelAttribute Item item) {
		
		database.updateItem(item);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/viewLocations")
	public String viewLocations(Model model, @ModelAttribute Location location) {
		
		List<Location> locations = database.getLocations();
		
		model.addAttribute("locations", locations);
		
		return "view_locations";
		
	}
	
	@PostMapping("/updateLocation")
	public String updateLocation(@ModelAttribute Location location) {
		
		database.updateLocation(location);
		
		return "redirect:/";
	}
	
	@GetMapping("/addLocation")
	public String addLocation(@ModelAttribute Location location, Model model) {
		
		return "add_location";
		
	}
	
	@PostMapping("/addLocation")
	public String addToLocations(@ModelAttribute Location location) {
		
		database.addLocation(location);
		
		return "redirect:/";
		
	}
	
}
