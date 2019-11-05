import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {
Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public void run() {
		try {
			socketClient= new Socket("173.167.129.37",5555);
		    out = new ObjectOutputStream(socketClient.getOutputStream());
		    in = new ObjectInputStream(socketClient.getInputStream());
		    socketClient.setTcpNoDelay(true);
			}
			catch(Exception e) {}
			
			while(true) {
				 
				try {
				String message = in.readObject().toString();
				
				}
				catch(Exception e) {}
			}
			
		
	}
	public void send(String data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
