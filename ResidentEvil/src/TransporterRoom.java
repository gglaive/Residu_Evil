/**
 * @author Geoffrey Glaive
 * @version 03.2019
 * Subclass of "Room" that rewrite the "getExit" method to use "RoomRandomizer".
 */

public class TransporterRoom extends Room{

	private RoomRandomizer roomRandomizer;
	
	public TransporterRoom(String description, String image, RoomRandomizer rr) {
		super(description, image);
		this.roomRandomizer = rr;
	}
	
	public Room getExit(String direction) {
		return roomRandomizer.findRandomRoom();
	}
	
}
