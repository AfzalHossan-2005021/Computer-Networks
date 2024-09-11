package Server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static Server.HTTPServer.*;

public class ReadWriteThread implements Runnable{
    private final Socket client;
    static final String ROOT = "Public";
    public ReadWriteThread(Socket client) {
        this.client = client;
        new Thread(this).start();
    }
    
    String handleTxtFile(String relativePath){
        StringBuilder body = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(relativePath))) {
            String line;
            body.append("<pre>");
            while ((line = br.readLine()) != null) {
                body.append(line).append("\n");
            }
            body.append("</pre>");
        } catch (FileNotFoundException e) {
            body.append("<p>File not found: ").append(relativePath).append("</p>");
        } catch (IOException e) {
            body.append("<p>Error reading file: ").append(e.getMessage()).append("</p>");
        }
        return body.toString();
    }
    String handleImageFile(String relativePath){
        StringBuilder body = new StringBuilder();
        Path imagePath = Paths.get(relativePath);
        try {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            String mimeType = Files.probeContentType(imagePath);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            body.append("<img src=\"data:").append(mimeType).append(";base64,").append(base64Image).append("\" />");
        } catch (IOException exception) {
            System.err.println("An error occurred while reading the file: " + exception.getMessage());
        }
        return body.toString();
    }
    String generateFileHtml(String relativePath){
        String body = "";
        if(relativePath.contains(".txt")){
            body += handleTxtFile(relativePath);
        } else if (relativePath.contains(".png") || relativePath.contains(".jpg")) {
            body += handleImageFile(relativePath);
        }
        return "<!DOCTYPE html>\n<html>\n<head>\n<title></title>\n</head>\n<body>\n" +
                body +
                "\n</body>\n</html>";
    }
    String generateDirectoryHtml(String[] directoryContents, String parent, String relativePath){
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title></title>\n</head>\n<body>\n");
        for (String item : directoryContents) {
            String url = parent + item;
            Path path = Paths.get(relativePath + "/" + item);
            if(Files.isDirectory(path)){
                html.append("<a href=\"")
                        .append(url)
                        .append("\" style=\"color: black; text-decoration: none;\">")
                        .append("<b><i>")
                        .append(item)
                        .append("</b></i>")
                        .append("</a><br>");
            } else {
                String mimeType;
                try {
                    mimeType = Files.probeContentType(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if(mimeType.contains("image") || mimeType.contains("text")) {
                    html.append("<a href=\"").append(url)
                            .append("\" style=\"color: black; text-decoration: none;\">")
                            .append(item)
                            .append("</a><br>");
                } else {
                    html.append("<a href=\"").append(url)
                            .append("\" style=\"color: black; text-decoration: none;\" download>")
                            .append(item)
                            .append("</a><br>");
                }
            }
        }
        html.append("\n</body>\n</html>");
        return html.toString();
    }
    String generateErrorMessage(){
        return """
                <!DOCTYPE html>
                <html>
                <head>
                <title></title>
                </head>
                <body>
                <h1>404: Page not found</h1>
                </body>
                </html>""";
    }
    @Override
    public void run(){
        try {
            SocketWrapper socketWrapper = new SocketWrapper(client);
            String request = socketWrapper.read();
            if(request != null && !request.isEmpty()){
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                socketWrapper.writeLog(timeStamp, true);
                socketWrapper.writeLog("Request:\n", false);
                socketWrapper.writeLog(request, true);
                socketWrapper.writeLog("\nResponse:\n", false);
                String[] partsOfRequest = request.split(" ");
                if(partsOfRequest[0].equals("GET")){
                    String relativePath = ROOT + partsOfRequest[1].replace("%20", " ");
                    File file = new File(relativePath);
                    Path path = Paths.get(relativePath);
                    if(file.exists()){
                        String mimeType;
                        try {
                            mimeType = Files.probeContentType(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (mimeType == null || mimeType.contains("image") || mimeType.contains("text")) {
                            mimeType = "text/html";
                        }
                        String htmlContent = "";
                        if(file.isDirectory()){
                            String[] contents = file.list();
                            if (contents != null) {
                                String parent = "http://localhost:" + PORT + partsOfRequest[1];
                                if(!partsOfRequest[1].endsWith("/")){
                                    parent += "/";
                                }
                                htmlContent += generateDirectoryHtml(contents, parent, relativePath);
                            }
                        } else if (file.isFile()) {
                            htmlContent += generateFileHtml(relativePath);
                        }
                        socketWrapper.write("HTTP/1.1 200 OK\r\n");
                        socketWrapper.write("Server: Java HTTP Server: 1.0\r\n");
                        socketWrapper.write("Date: " + new Date() + "\r\n");
                        socketWrapper.write("Content-Type: " + mimeType + "\r\n");
                        if(mimeType.equals("text/html")){
                            socketWrapper.write("Content-Length: " + htmlContent.length() + "\r\n");
                            socketWrapper.write("\r\n");
                            socketWrapper.write(htmlContent);
                            socketWrapper.flush();
                        } else {
                            String[] filePath = partsOfRequest[1].split("/");
                            String fileName = filePath[filePath.length-1];
                            socketWrapper.write("Content-Disposition: attachment; filename=\"" + fileName + "\"\r\n");
                            socketWrapper.write("Content-Length: " + (int)file.length() + "\r\n");
                            socketWrapper.write("\r\n");
                            socketWrapper.flush();
                            OutputStream output = client.getOutputStream();
                            try (FileInputStream fileInput = new FileInputStream(file)) {
                                byte[] buffer = new byte[CHUNK_SIZE];
                                int bytesRead;
                                while ((bytesRead = fileInput.read(buffer)) != -1) {
                                    output.write(buffer, 0, bytesRead);
                                }
                                output.flush();
                            }
                        }
                    } else {
                        String errorMessage = generateErrorMessage();
                        socketWrapper.write("HTTP/1.1 404 NOT FOUND\r\n");
                        socketWrapper.write("Server: Java HTTP Server: 1.0\r\n");
                        socketWrapper.write("Date: " + new Date() + "\r\n");
                        socketWrapper.write("Content-Type: text/html\r\n");
                        socketWrapper.write("Content-Length: " + errorMessage.length() + "\r\n");
                        socketWrapper.write("\r\n");
                        socketWrapper.write(errorMessage);
                        socketWrapper.flush();
                        System.out.println("404: Page not found");
                    }
                }
            }
            socketWrapper.closeConnection();
        } catch (IOException e) {
            try {
                client.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}