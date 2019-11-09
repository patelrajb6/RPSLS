import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;



public class Server{
	int count = 1;	// will count the number of games played
	Boolean clientA,clientB;
	GameInfo clientInfo2;			//reference to the game
	ObjectOutputStream out;		//output stream of the server
	ObjectInputStream in;			//input of the stream
	int portNum;		//getting the port number
	ClientThread[] player= new ClientThread[2]; // knowing only two players will be there
	ServerThread server;
	private Consumer<GameInfo> callback;

	Server(String port,Consumer<GameInfo> call){
		try {
		   portNum = Integer.parseInt(port) ;		//error checking and converting the portnum
		}catch(Exception e){}
		clientInfo2 =new GameInfo();
		server = new ServerThread(portNum);
		callback=call;
		server.start();
	}
	
	class ServerThread extends Thread{		//creating the server thread
		int port;
		
		ServerThread(int num)
		{
			this.port = num;
		}
		
		
		
		public void run() {		//the thread executes the following thread method
			
			try(ServerSocket mysocket = new ServerSocket(port);){		//creates new socket for the clients to connect to
		    System.out.println("Server is waiting for a client!");
			
		    while(count<=2) {				//can only have 2 clients
		
				player[count-1] = new ClientThread(mysocket.accept(), count);
				
				System.out.println("client has connected to server: " + "client #" + count);
				
				
				player[count-1].start();
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					
				}
			}//end of while
		
	}
	
	class ClientThread extends Thread{			//the thread on which each client will run
		Socket connection;
		int count;
		ObjectInputStream in;			//their input and output streams for comm.
		ObjectOutputStream out;
		ClientThread(Socket s, int count)		
		{
			
			this.connection=s;		
			this.count=count;
			
		}
		void updateConnection(GameInfo clientInfo)		//updating connection to mark if its the first client or the second
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
		
		
		void updateClients(GameInfo game)		//updating the clients accordingly
		 {
			try
			 {
				 
				 player[0].out.writeObject(game);
				 player[1].out.writeObject(game);
			 
			 }
			 catch(Exception e) {}
			 
			
			 
		 }
		
		
		public void run(){
			try {
				out = new ObjectOutputStream(connection.getOutputStream());		//opens the streams
				in = new ObjectInputStream(connection.getInputStream());
				
				updateConnection(clientInfo2);				//updates the connection
				updateClients(clientInfo2);					//updates the client
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}
			
		while(true)
		{
			try {
				
			    GameInfo clientInfo=(GameInfo)in.readObject();		//reading from the incoming data
			   
			    clientInfo2=clientInfo;
		    	callback.accept(clientInfo);		//displaying and using the info send for gui
		    	System.out.println(clientInfo.getPlayer1Choice());	//debugging
		    	System.out.println(clientInfo.getPlayer2Choice());
		    	System.out.println(clientInfo.evaluate());
		    	System.out.println( "both "+(clientInfo2.client1 && clientInfo2.client2));
		    	
		    	
		    	
		    	
	    		
			   	//updating the clients
		    	updateClients(clientInfo2);
				connection.setTcpNoDelay(true);	
			}
			catch(Exception e) {
				//System.out.println("Streams not open");
			}
		}
			
	}	
}
}