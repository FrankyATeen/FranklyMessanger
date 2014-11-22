package franklyateen.franklymessanger.client;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class Client extends JFrame{
private static final long serialVersionUID = 3055808167198355764L;
	
	public int serverPort = 54321;
	public String ip = "localHost";
	
	public static boolean connected = false;
	
	public static Socket client;
	public static ObjectInputStream in;
	public static ObjectOutputStream out;
	
	int roomAPCS = 0, roomGossip = 1;
	
	private JPanel login;
	private JPanel clientAfterLogin;
	private JLabel loginTitle;
	private JLabel loginDesc;
	private JLabel loginLblUsername;
	private JTextField loginPutUsername;
	private JLabel loginLblPassword;
	private JPasswordField loginPutPassword;
	private JButton loginButtonLogin;
	private JButton loginButtonCreate;
	private JCheckBox loginchckbxRemUsername;
	private JTextArea textArea;
	private JButton btnEnter;
	private JScrollPane scrollPane;
	
	private CardLayout screenHandler;
	
	
	public Client(){
		displayJFrame();
		try {
			client = new Socket(ip, serverPort);
			out = new   ObjectOutputStream(client.getOutputStream());
			in = new   ObjectInputStream(client.getInputStream());
			connected = true;
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		listenForMessage test = new listenForMessage();
		test.start();
	}
	
	public void displayJFrame(){
		setLayout(new CardLayout(0, 0));
		
		login = new JPanel();
		add(login, "Login");
		SpringLayout sl_login = new SpringLayout();
		login.setLayout(sl_login);
		
		loginTitle = new JLabel("Welcome to The Messanger");
		loginTitle.setFont(new Font("Tahoma", Font.PLAIN, 63));
		sl_login.putConstraint(SpringLayout.NORTH, loginTitle, 10, SpringLayout.NORTH, login);
		sl_login.putConstraint(SpringLayout.WEST, loginTitle, 10, SpringLayout.WEST, login);
		sl_login.putConstraint(SpringLayout.SOUTH, loginTitle, 167, SpringLayout.NORTH, login);
		sl_login.putConstraint(SpringLayout.EAST, loginTitle, 790, SpringLayout.WEST, login);
		login.add(loginTitle);
		
		loginDesc = new JLabel("Please Login");
		loginDesc.setHorizontalAlignment(SwingConstants.CENTER);
		loginDesc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sl_login.putConstraint(SpringLayout.NORTH, loginDesc, 8, SpringLayout.SOUTH, loginTitle);
		sl_login.putConstraint(SpringLayout.WEST, loginDesc, 238, SpringLayout.WEST, login);
		sl_login.putConstraint(SpringLayout.SOUTH, loginDesc, 34, SpringLayout.SOUTH, loginTitle);
		sl_login.putConstraint(SpringLayout.EAST, loginDesc, -236, SpringLayout.EAST, login);
		login.add(loginDesc);
		
		loginLblUsername = new JLabel("Username");
		sl_login.putConstraint(SpringLayout.NORTH, loginLblUsername, 19, SpringLayout.SOUTH, loginDesc);
		sl_login.putConstraint(SpringLayout.WEST, loginLblUsername, 294, SpringLayout.WEST, login);
		sl_login.putConstraint(SpringLayout.SOUTH, loginLblUsername, -340, SpringLayout.SOUTH, login);
		sl_login.putConstraint(SpringLayout.EAST, loginLblUsername, -436, SpringLayout.EAST, login);
		loginLblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		login.add(loginLblUsername);
		
		loginLblPassword = new JLabel("Password");
		sl_login.putConstraint(SpringLayout.NORTH, loginLblPassword, 6, SpringLayout.SOUTH, loginLblUsername);
		sl_login.putConstraint(SpringLayout.EAST, loginLblPassword, 0, SpringLayout.EAST, loginLblUsername);
		loginLblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		login.add(loginLblPassword);
		
		loginPutUsername = new JTextField();
		sl_login.putConstraint(SpringLayout.NORTH, loginPutUsername, 27, SpringLayout.SOUTH, loginDesc);
		sl_login.putConstraint(SpringLayout.WEST, loginPutUsername, 19, SpringLayout.EAST, loginLblUsername);
		sl_login.putConstraint(SpringLayout.EAST, loginPutUsername, -281, SpringLayout.EAST, login);
		loginPutUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		login.add(loginPutUsername);
		loginPutUsername.setColumns(10);
		
		loginPutPassword = new JPasswordField();
		sl_login.putConstraint(SpringLayout.NORTH, loginPutPassword, 12, SpringLayout.SOUTH, loginPutUsername);
		sl_login.putConstraint(SpringLayout.EAST, loginPutPassword, 0, SpringLayout.EAST, loginPutUsername);
		loginPutPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginPutPassword.setEchoChar('*');
		sl_login.putConstraint(SpringLayout.WEST, loginPutPassword, 19, SpringLayout.EAST, loginLblPassword);
		sl_login.putConstraint(SpringLayout.SOUTH, loginPutPassword, 0, SpringLayout.SOUTH, loginLblPassword);
		login.add(loginPutPassword);
		
		loginButtonLogin = new JButton("Login");
		sl_login.putConstraint(SpringLayout.NORTH, loginButtonLogin, 51, SpringLayout.SOUTH, loginLblPassword);
		sl_login.putConstraint(SpringLayout.WEST, loginButtonLogin, 294, SpringLayout.WEST, login);
		loginButtonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		login.add(loginButtonLogin);
		
		loginButtonCreate = new JButton("Create");
		sl_login.putConstraint(SpringLayout.EAST, loginButtonLogin, -10, SpringLayout.WEST, loginButtonCreate);
		sl_login.putConstraint(SpringLayout.NORTH, loginButtonCreate, 0, SpringLayout.NORTH, loginButtonLogin);
		sl_login.putConstraint(SpringLayout.WEST, loginButtonCreate, 411, SpringLayout.WEST, login);
		sl_login.putConstraint(SpringLayout.EAST, loginButtonCreate, 0, SpringLayout.EAST, loginPutUsername);
		login.add(loginButtonCreate);
		
		loginchckbxRemUsername = new JCheckBox("Remeber Username");
		sl_login.putConstraint(SpringLayout.NORTH, loginchckbxRemUsername, 6, SpringLayout.SOUTH, loginLblPassword);
		sl_login.putConstraint(SpringLayout.WEST, loginchckbxRemUsername, 0, SpringLayout.WEST, loginLblUsername);
		login.add(loginchckbxRemUsername);
		
		clientAfterLogin = new JPanel();
		add(clientAfterLogin, "client");
		SpringLayout sl_clientAfterLogin = new SpringLayout();
		clientAfterLogin.setLayout(sl_clientAfterLogin);
		
		btnEnter = new JButton("Enter");
		sl_clientAfterLogin.putConstraint(SpringLayout.EAST, btnEnter, -10, SpringLayout.EAST, clientAfterLogin);
		sl_clientAfterLogin.putConstraint(SpringLayout.SOUTH, btnEnter, -10, SpringLayout.SOUTH, clientAfterLogin);
		clientAfterLogin.add(btnEnter);
		
		scrollPane = new JScrollPane();
		sl_clientAfterLogin.putConstraint(SpringLayout.NORTH, scrollPane, 167, SpringLayout.NORTH, clientAfterLogin);
		sl_clientAfterLogin.putConstraint(SpringLayout.WEST, scrollPane, 216, SpringLayout.WEST, clientAfterLogin);
		sl_clientAfterLogin.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, btnEnter);
		sl_clientAfterLogin.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, clientAfterLogin);
		clientAfterLogin.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		sl_clientAfterLogin.putConstraint(SpringLayout.NORTH, textArea, 132, SpringLayout.NORTH, clientAfterLogin);
		sl_clientAfterLogin.putConstraint(SpringLayout.WEST, textArea, 261, SpringLayout.WEST, clientAfterLogin);
		sl_clientAfterLogin.putConstraint(SpringLayout.EAST, textArea, -10, SpringLayout.EAST, clientAfterLogin);
		sl_clientAfterLogin.putConstraint(SpringLayout.SOUTH, textArea, 13, SpringLayout.NORTH, btnEnter);
		
		screenHandler = (CardLayout) this.getLayout();
	}
	
	
	
	public void addChatMessage(String username,String toPrint){
		String toAdd = "\n" + username + ": " + toPrint;
	}
	
	public void updateUserList(){
	}
	
	
	
	
	class listenForMessage extends Thread{
		listenForMessage(){
			

		}
		
		@Override
		public void run() {
			while(connected){

			}
			
			try {
				in.close();
				out.close();
				client.close();
				System.exit(-1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
	}
}
