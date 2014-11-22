package franklyateen.franklymessanger.utility;

public class MessagePacket extends Packet {

	private String message;
	
	public MessagePacket(String message) {
		super(PacketType.Message);
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}

}
