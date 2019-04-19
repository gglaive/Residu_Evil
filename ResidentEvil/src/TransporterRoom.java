
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
