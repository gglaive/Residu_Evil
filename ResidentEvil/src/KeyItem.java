
public class KeyItem extends Item{

	private Room room;
	private int needed;
	private String useOn;
	
	public KeyItem(String name, String description, int number, int inventory_space){
		super(name, description, number, inventory_space);
	}
	
	public Room getRoom(){
		return room;
	}
	
	public void setRoom(Room room){
		this.room = room;
	}
	
	public int getNeeded(){
		return needed;
	}
	
	public void setNeeded(int needed){
		this.needed = needed;
	}
	
	public String getUseOn(){
		return useOn;
	}
	
	public void SetUseOn(String useOn){
		this.useOn = useOn;
	}
}
