package Server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    static final int PORT = 5021;
    static final String ROOT = "Public";
    static final int CHUNK_SIZE = 1024;
    static final String LOG_FILE_PATH = "log.txt";
    public static void main(String[] args) {
        File rootDirectory = new File(ROOT);
        if((rootDirectory.exists() && rootDirectory.isDirectory()) || rootDirectory.mkdir()) {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
                while (true){
                    Socket client = serverSocket.accept();
                    new ReadWriteThread(client);
                }
            } catch (IOException ioException) {
                System.out.println(ioException.getMessage());
            }
        } else {
            System.out.println("Root directory in not found or created.");
        }
    }
}
