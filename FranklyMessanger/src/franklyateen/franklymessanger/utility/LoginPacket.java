package franklyateen.franklymessanger.utility;

public class LoginPacket extends Packet {

	private String username;
	private char[] password;
	
	public LoginPacket(String username, char[] password) {
		super(PacketType.Login);
		this.username = username;
		this.password = password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public char[] getPassword(){
		return password;
	}

}
