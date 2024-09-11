package Server;

import java.io.*;
import java.net.Socket;

import static Server.HTTPServer.LOG_FILE_PATH;

public class SocketWrapper {
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;
    private FileWriter logFileWriter;
    private final BufferedWriter logPbufferedWriter;
    private final PrintWriter logPrintWriter;

    public SocketWrapper(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream(), false);
        try {
            this.logFileWriter = new FileWriter(LOG_FILE_PATH, true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.logPbufferedWriter = new BufferedWriter(logFileWriter);
        this.logPrintWriter = new PrintWriter(logPbufferedWriter, true);
    }

    public String read() throws IOException{
        return bufferedReader.readLine();
    }

    public void write(String str) {
        printWriter.write(str);
        logPrintWriter.write(str);
    }

    public void flush(){
        printWriter.flush();
    }

    public void writeLog(String str, boolean addNewLine){
        if (addNewLine) {
            logPrintWriter.println(str);
        } else {
            logPrintWriter.write(str);
        }
    }

    public void closeConnection() throws IOException {
        bufferedReader.close();
        printWriter.close();
        logPrintWriter.close();
        logPbufferedWriter.close();
        logFileWriter.close();
    }
}