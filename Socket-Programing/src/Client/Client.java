package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static final String HOST = "localhost";
    static final int PORT = 5021;
    static final int CHUNK_SIZE = 1024;
    public static void main(String[] args) {
        while (true) {
            try {
                Socket socket = new Socket(HOST, PORT);
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                new RequestHandler(socket, input);
            } catch (IOException exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
