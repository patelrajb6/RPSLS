
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;


public class Server{
	int count = 1;	// will count the number of games played
	GameInfo ClientInfo;
	ClientThread player1,player2; // knowing only two players will be there
	ServerThread server;
	
	
	Server(){
		
		
		server = new ServerThread();
		server.start();
	}
	
	class ServerThread extends Thread{
		public void run() {
			
			try(ServerSocket mysocket = new ServerSocket(5555);){
		    System.out.println("Server is waiting for a client!");
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				
				System.out.println("client has connected to server: " + "client #" + count);
				
				
				
				c.start();
				
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {

				}
			}//end of while
		
	}
	
	class ClientThread extends Thread{
		Socket connection;
		int count;
		ObjectInputStream in;
		ObjectOutputStream out;
		ClientThread(Socket s, int count)
		{
			this.connection=s;		
			this.count=count;
		}
		
		public void run(){
			
			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
			    System.out.println((String)in.readObject());
				connection.setTcpNoDelay(true);	
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}
	}
}
}