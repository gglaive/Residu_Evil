import java.util.ArrayList;
import java.util.Random;

/**
 * @author Geoffrey Glaive
 * @version 04.2019
 * Class used for randomizing an exit for a "Room' element.
 */

public class RoomRandomizer {

	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	public RoomRandomizer(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public Room findRandomRoom() {
		
		Random random = new Random();
		Room randomRoom = rooms.get(random.nextInt(rooms.size()));
		return randomRoom;
	}
}
