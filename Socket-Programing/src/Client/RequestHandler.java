package Client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {
    static final String HOST = "localhost";
    static final int PORT = 5021;
    static final int CHUNK_SIZE = 1024;
    private final String request;

    public RequestHandler(String request) {
        this.request = request;
        new Thread(this).start();
    }

    private void writeLine(String line, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void run() {
        if (request.startsWith("UPLOAD ")) {
            String filePath = request.replace("UPLOAD ", "").trim();
            File file = new File(filePath);
            if (file.exists()) {
                try {
                    Socket socket = new Socket(HOST, PORT);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    String uploadRequest = "UPLOAD " + file.getName();
                    writeLine(uploadRequest, bufferedWriter);
                    Path path = Paths.get(filePath);
                    String mimeType = Files.probeContentType(path);
                    writeLine(mimeType, bufferedWriter);
                    String status = bufferedReader.readLine();
                    if (status.equals("200")) {
                        try {
                            FileInputStream fileInputStream = new FileInputStream(file);
                            byte[] buffer = new byte[CHUNK_SIZE];
                            int bytesRead;
                            try {
                                while ((bytesRead = fileInputStream.read(buffer)) != -1){
                                    outputStream.write(buffer, 0, bytesRead);
                                }
                                fileInputStream.close();
                                outputStream.close();
                                System.out.println("File upload successful.");
                            } catch (IOException exception) {
                                System.out.println(exception.getMessage());
                                System.out.println("File upload failed.");
                            }
                        } catch (FileNotFoundException exception) {
                            System.out.println(exception.getMessage());
                        }
                    } else {
                        System.out.println("The given file name or format is invalid");
                    }
                    bufferedReader.close();
                    bufferedWriter.close();
                    socket.close();
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            } else {
                System.out.println("File does not exist.");
            }
        } else {
            System.out.println("Invalid request.");
        }
    }
}