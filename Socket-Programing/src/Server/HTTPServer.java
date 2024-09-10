package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    static final int PORT = 5021;
    public static void main(String[] args) throws IOException {
        System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true){
            Socket client = serverSocket.accept();
            new ReadWriteThread(client);
        }
    }
}
