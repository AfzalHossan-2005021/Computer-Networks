package Client;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            new RequestHandler(input);
        }
    }
}
