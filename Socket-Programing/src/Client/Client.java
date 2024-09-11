package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static final String HOST = "localhost";
    static final int PORT = 5021;
    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)){
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

            }
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
