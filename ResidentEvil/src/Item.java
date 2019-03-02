import java.util.ArrayList;

public class Item {

	private String name;
	private String description;
	private int number;
	public int initial_number;

	public Item(String name, String description, int number) {
		this.name = name;
		this.description = description;
		this.number = number;
		initial_number = number;
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
