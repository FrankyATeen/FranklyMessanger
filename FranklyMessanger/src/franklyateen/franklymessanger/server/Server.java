package franklyateen.franklymessanger.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	static Socket socket;
	static ArrayList<Server> clients;
	
	ObjectInputStream in;
	ObjectOutputStream out;
	int roomAPCS = 0, roomGossip = 1;
	int roomIn;
	int userID;
	String username;
	
   Server(Socket csocket, int userID) {
        Server.socket = csocket;
        roomIn = roomAPCS;
        this.userID = userID;
   }

   // gets connections from clients then starts a thread for each
   public static void main(String args[]) throws Exception {
	   	clients = new ArrayList<Server>();
	  
	   	
        @SuppressWarnings("resource")
		ServerSocket ssock = new ServerSocket(54321);
        
        System.out.println("Listening");
        int clientID = -1;
        while (true) {
            Socket sock = ssock.accept();
            clientID++;
            InetAddress addr = sock.getInetAddress();
            System.out.println("Connection made to " + addr.getHostName() + " (" + addr.getHostAddress() + ") ID: " + clientID);
            Server newClient = new Server(sock, clientID);
            
            clients.add(newClient);
        }
    }

   	// thread for each client
    public void run() {
        try {
            in = new   ObjectInputStream(socket.getInputStream());
            out = new  ObjectOutputStream(socket.getOutputStream());
            
         
           
         
           while (true) {
                if (!socket.isConnected()) {
                    System.out.println("NOT CONNECTED ANYMORE!!");
                    break;
                }
                
             
            }
            in.close();
            out.close();
            socket.close();
            System.exit(-1);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void updateClientsUserList(){
    	// update Clients users in room
    }

}
