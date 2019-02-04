
public class Item {

	private String name;
	private int number;
	public int initial_number;
	private Room room;;

	public Item(String name, int number) {
		this.name = name;
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
	
	public Room getRoom(){
		return room;
	}
	
	public void setRoom(Room room){
		this.room = room;
	}
	
}
