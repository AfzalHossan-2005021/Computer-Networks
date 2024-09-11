package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    static final int PORT = 5021;
    static final int CHUNK_SIZE = 1024;
    static final String LOG_FILE_PATH = "log.txt";
    public static void main(String[] args) throws IOException {
        System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true){
            Socket client = serverSocket.accept();
            System.out.println("Accepted");
            new ReadWriteThread(client);
        }
    }
}
