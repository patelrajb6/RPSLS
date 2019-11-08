import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {
	Socket socketClient;
	GameInfo info;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	String ipAddr;
	int portNum;
	
	
	Client(String ip, String port)
	{
		try {
			   ipAddr = ip;
			   portNum = Integer.parseInt(port) ;
			}catch(Exception e){}
		info= new GameInfo();
	}
	public void run() {
		try {
			socketClient= new Socket(ipAddr,portNum);
		    out = new ObjectOutputStream(socketClient.getOutputStream());
		    in = new ObjectInputStream(socketClient.getInputStream());
		    socketClient.setTcpNoDelay(true);
			}
			catch(Exception e) {}
			
			while(true) {
				 
				try {
				info = (GameInfo)in.readObject();
				send(new GameInfo());
				
				}
				catch(Exception e) {}
			}
			
	}
	public void send(GameInfo info) {
		
		try {
			out.writeObject(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}