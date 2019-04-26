package pkg_item;
import java.util.ArrayList;

/**
 * @author Geoffrey Glaive
 * @version 03.2019
 * Class that instantiates "item" elements.
 */

public class Item {

	private String name;
	private String description;
	private int number;
	public int initial_number;
	private int inventory_space;

	public Item(String name, String description, int number, int inventory_space) {
		this.name = name;
		this.description = description;
		this.number = number;
		initial_number = number;
		this.inventory_space = inventory_space;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getInventory_space() {
		return inventory_space;
	}

	public void setInventory_space(int inventory_space) {
		this.inventory_space = inventory_space;
	}

	static public Item findByName(String s, ArrayList<Item> L) {
        int n=0;
        while (n < L.size()) {
            Item i = L.get(n);
            if (s.equals(i.getName())) 
                return i;
            n++;
        }
        return null;
    }
	
}
