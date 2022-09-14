package chat.server.chatServer.server.controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import chat.server.chatServer.server.tasks.ChatServerReciever;
import chat.server.chatServer.server.tasks.ChatServerSender;
import chat.server.mediation.BlkQueue;
import chat.server.mediation.IBlkQueue;



public class ChatServerAppl {

	public static void main(String[] args) {
		int port = 9000;
		IBlkQueue<String> messageBox = new BlkQueue<>(10);
		ChatServerSender sender = new ChatServerSender(messageBox);
		Thread senderThread = new Thread(sender);
		senderThread.setDaemon(true);
		senderThread.start();
		ExecutorService executorService = Executors.newFixedThreadPool(11);
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			try {
				while (true) {
					System.out.println("Server waiting...");
					Socket socket = serverSocket.accept();
					System.out.println("Connection established");
					System.out.println("Client address: " + socket.getInetAddress() + ":" + socket.getPort());
					sender.addClient(socket);
					ChatServerReciever receiver = new ChatServerReciever(socket, messageBox);
					executorService.execute(receiver);
				}
			} finally {
				executorService.shutdown();
				executorService.awaitTermination(1, TimeUnit.MINUTES);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}