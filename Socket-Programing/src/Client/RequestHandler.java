package Client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Client.Client.CHUNK_SIZE;

public class RequestHandler implements Runnable {
    private final Socket socket;
    private final String request;
    private final OutputStream outputStream;
    private final BufferedWriter bufferedWriter;
    public RequestHandler(Socket socket, String request) throws IOException {
        this.socket = socket;
        this.request = request;
        this.outputStream = socket.getOutputStream();
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        new Thread(this).start();
    }

    private void writeLine(String line) {
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
            if (file.exists()){
                Path path = Paths.get(filePath);
                try {
                    String mimeType = Files.probeContentType(path);
                    if (mimeType.contains("image") || mimeType.contains("text")) {
                        String uploadRequest = "UPLOAD " + file.getName();
                        writeLine(uploadRequest);
                        try {
                            FileInputStream fis = new FileInputStream(file);
                            byte[] buffer = new byte[CHUNK_SIZE];
                            int bytesRead;
                            while (true) {
                                try {
                                    if ((bytesRead = fis.read(buffer)) == -1) break;
                                    outputStream.write(buffer, 0, bytesRead);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            try {
                                fis.close();
                            } catch (IOException exception) {
                                System.out.println(exception.getMessage());
                            }
                        } catch (FileNotFoundException exception) {
                            System.out.println(exception.getMessage());
                        }
                    } else {
                        writeLine("UPLOAD:ERROR");
                        System.out.println("The given file name or format is invalid");
                    }
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }
            } else {
                writeLine("UPLOAD:ERROR");
                System.out.println("The given file name or format is invalid");
            }
        } else {
            writeLine("UPLOAD:ERROR");
            System.out.println("The given file name or format is invalid");
        }
        try {
            outputStream.close();
            bufferedWriter.close();
            socket.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
