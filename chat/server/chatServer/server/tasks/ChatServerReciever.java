package chat.server.chatServer.server.tasks;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import chat.server.mediation.IBlkQueue;



public class ChatServerReciever implements Runnable {
	Socket socket;
	IBlkQueue<String> messageBox;

	public ChatServerReciever(Socket socket, IBlkQueue<String> messageBox) {
		this.socket = socket;
		this.messageBox = messageBox;
	}

	@Override
	public void run() {
		try (Socket socket = this.socket) {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			while (true) {
				String message = ois.readObject().toString();
				messageBox.push(message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Connection to client: " + socket.getInetAddress() + ":" + socket.getPort() + " closed");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
