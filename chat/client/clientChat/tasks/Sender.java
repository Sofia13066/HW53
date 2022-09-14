package chat.client.clientChat.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;

import chat.client.model.Message;

public class Sender implements Runnable {
	Socket socket;

	public Sender(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (Socket socket = this.socket) {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter your name:");
			String name = br.readLine();
			System.out.println("Please enter your message, or type exit for quit");
			String message = br.readLine();
			while (!"exit".equalsIgnoreCase(message)) {
                Message actualMsg = new Message(name, null, message);
				oos.writeObject(actualMsg);
				message = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
