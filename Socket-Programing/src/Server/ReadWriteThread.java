package Server;

import java.io.File;
import java.io.IOException;
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
    String generateHtml(String[] directoryContents){
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n<title></title>\n</head>\n<body>\n");
        for (String item : directoryContents) {
            if(!item.contains(".")){
                html.append("<b><i>");
            }
            html.append(item);
            if(!item.contains(".")){
                html.append("</b></i>");
            }
            html.append("<br>");
        }
        html.append("</body>").append("</html");
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
                    String[] contents = path.list();
                    if (contents != null) {
                        String htmlContent = generateHtml(contents);
                        socketWrapper.write("HTTP/1.1 200 OK\r\n");
                        socketWrapper.write("Server: Java HTTP Server: 1.0\r\n");
                        socketWrapper.write("Date: " + new Date() + "\r\n");
                        socketWrapper.write("Content-Type: text/html\r\n");
                        socketWrapper.write("Content-Length: " + htmlContent.length() + "\r\n");
                        socketWrapper.write("\r\n");
                        socketWrapper.write(htmlContent);
                        socketWrapper.flush();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}