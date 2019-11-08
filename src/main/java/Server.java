import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;



public class Server{
	int count = 1;	// will count the number of games played
	
	GameInfo clientInfo2;
	ObjectOutputStream out;
	ObjectInputStream in;
	int portNum;
	ClientThread[] player= new ClientThread[2]; // knowing only two players will be there
	ServerThread server;
	private Consumer<GameInfo> callback;

	Server(String port,Consumer<GameInfo> call){
		try {
		   portNum = Integer.parseInt(port) ;
		}catch(Exception e){}
		clientInfo2 =new GameInfo();
		server = new ServerThread(portNum);
		callback=call;
		server.start();
	}
	
	class ServerThread extends Thread{
		int port;
		
		ServerThread(int num)
		{
			this.port = num;
		}
		
		
		
		public void run() {
			
			try(ServerSocket mysocket = new ServerSocket(port);){
		    System.out.println("Server is waiting for a client!");
			
		    while(count<=2) {
		
				player[count-1] = new ClientThread(mysocket.accept(), count);
				
				System.out.println("client has connected to server: " + "client #" + count);
				
				
				player[count-1].start();
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					//callback.accept("Server socket did not launch");
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
		void updateConnection(GameInfo clientInfo)
		{
			if(count == 1)
			{
				
				clientInfo.setPlayer1Connection(true);
			}
			if(count == 2)
			{
				
				clientInfo.setPlayer1Connection(true);clientInfo.setPlayer2Connection(true);
			}
		}
		
		
		void updateClients(GameInfo game)
		 {
			 
			 try
			 {
				 
				 player[0].out.writeObject(game);
				 player[1].out.writeObject(game);
			 
			 }
			 catch(Exception e) {}
			 
		 }
		void resetChoices()
		{
			clientInfo2.setPlayer1Choice("a");
			clientInfo2.setPlayer2Choice("a");
		}
		
		public void run(){
			try {
				out = new ObjectOutputStream(connection.getOutputStream());
				in = new ObjectInputStream(connection.getInputStream());
				
				updateConnection(clientInfo2);
				updateClients(clientInfo2);
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}
			
		while(true)
		{
			try {
				
			    GameInfo clientInfo=(GameInfo)in.readObject();
			    clientInfo2=clientInfo;
		    	callback.accept(clientInfo2);
		    	System.out.println(clientInfo2.getPlayer1Choice());
		    	System.out.println(clientInfo2.getPlayer2Choice());
		    	System.out.println(clientInfo2.evaluate());
		    	
			    updateClients(clientInfo2);
			    resetChoices();
				connection.setTcpNoDelay(true);	
			}
			catch(Exception e) {
				//System.out.println("Streams not open");
			}
		}
			
	}	
}
}