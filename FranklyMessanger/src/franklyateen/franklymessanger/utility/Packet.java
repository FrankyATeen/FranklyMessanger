package franklyateen.franklymessanger.utility;


public class Packet{

	
	protected PacketType type;
	
	
	public Packet(PacketType type){
		this.type = type;
	}
	
	public PacketType getType(){
		return type;
	}
}
