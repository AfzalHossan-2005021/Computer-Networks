package Server;

import java.io.*;
import java.net.Socket;
import java.util.Date;

import static Server.HTTPServer.PORT;

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
            body.append("<pre>");  // Preserve formatting with <pre> tag
            while ((line = br.readLine()) != null) {
                body.append(line).append("\n");  // Output each line
            }
            body.append("</pre>");
        } catch (FileNotFoundException e) {
            body.append("<p>File not found: " + relativePath + "</p>");
        } catch (IOException e) {
            body.append("<p>Error reading file: " + e.getMessage() + "</p>");
        }
        return body.toString();
    }
    String handleImageFile(String relativePath){
        String body = "";
        return  body;
    }
    String handleOtherFile(String relativePath){
        String body = "";
        return  body;
    }
    String generateFileHtml(String relativePath){
        String body = "";
        if(relativePath.contains(".txt")){
            body += handleTxtFile(relativePath);
        } else if (relativePath.contains(".png") || relativePath.contains(".jpg")) {
            body += handleImageFile(relativePath);
        } else {
            body += handleOtherFile(relativePath);
        }
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title></title>\n</head>\n<body>\n");
        html.append(body);
        html.append("\n</body>\n</html>");
        return html.toString();
    }
    String generateDirectoryHtml(String[] directoryContents, String parent){
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title></title>\n</head>\n<body>\n");
        for (String item : directoryContents) {
            html.append("<a href=\"").append(parent).append(item)
                    .append("\" style=\"color: black; text-decoration: none;\">");
            if(!item.contains(".")){
                html.append("<b><i>");
            }
            html.append(item);
            if(!item.contains(".")){
                html.append("</b></i>");
            }
            html.append("</a><br>");
        }
        html.append("\n</body>\n</html>");
        return html.toString();
    }
    String generateErrorMessage(){
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title></title>\n</head>\n<body>\n");
        html.append("<h6>404: Page not found</h6>");
        html.append("\n</body>\n</html>");
        return html.toString();
    }
    @Override
    public void run(){
        try {
            SocketWrapper socketWrapper = new SocketWrapper(client);
            String request = socketWrapper.read();
            if(request != null && !request.isEmpty()){
                String[] partsOfRequest = request.split(" ");
                if(partsOfRequest[0].equals("GET")){
                    String relativePath = ROOT + partsOfRequest[1].replace("%20", " ");
                    File path = new File(relativePath);
                    if(path.exists()){
                        String htmlContent = "";
                        if(path.isDirectory()){
                            String[] contents = path.list();
                            if (contents != null) {
                                String parent = "http://localhost:" + PORT + partsOfRequest[1];
                                if(!partsOfRequest[1].endsWith("/")){
                                    parent += "/";
                                }
                                htmlContent += generateDirectoryHtml(contents, parent);
                            }
                        } else if (path.isFile()) {
                            htmlContent += generateFileHtml(relativePath);
                        }
                        socketWrapper.write("HTTP/1.1 200 OK\r\n");
                        socketWrapper.write("Server: Java HTTP Server: 1.0\r\n");
                        socketWrapper.write("Date: " + new Date() + "\r\n");
                        socketWrapper.write("Content-Type: text/html\r\n");
                        socketWrapper.write("Content-Length: " + htmlContent.length() + "\r\n");
                        socketWrapper.write("\r\n");
                        socketWrapper.write(htmlContent);
                        socketWrapper.flush();
                    } else {
                        String errorMessage = generateErrorMessage();
                        socketWrapper.write("HTTP/1.1 r 404 NOT FOUND\r\n");
                        socketWrapper.write("Server: Java HTTP Server: 1.0\r\n");
                        socketWrapper.write("Date: " + new Date() + "\r\n");
                        socketWrapper.write("Content-Type: html\r\n");
                        socketWrapper.write("Content-Length: " + errorMessage.length() + "\r\n");
                        socketWrapper.write("\r\n");
                        socketWrapper.write(errorMessage);
                        socketWrapper.flush();
                        System.out.println("404: Page not found");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}