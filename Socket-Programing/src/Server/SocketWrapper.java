package Server;

import java.io.*;
import java.net.Socket;

public class SocketWrapper {
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;

    public SocketWrapper(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream());
    }

    public String read() throws IOException{
        return bufferedReader.readLine();
    }

    public void write(String str) {
        printWriter.write(str);
    }

    public void flush(){
        printWriter.flush();
    }

    public void closeConnection() throws IOException {
        bufferedReader.close();
        printWriter.close();
    }
}