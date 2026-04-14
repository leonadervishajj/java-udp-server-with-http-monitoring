import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;

public class HttpServer implements Runnable {
    
    @Override
    public void run() {
        try {

            com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8080), 0);
            
            server.createContext("/stats", (exchange) -> {
                String response = "<html><body>" +
                                  "<h1>📊 UDP Server Monitoring</h1>" +
                                  "<p><b>Statusi:</b> ONLINE</p>" +
                                  "<p><b>Porti UDP:</b> 1234</p>" +
                                  "<p><b>Porti HTTP:</b> 8080</p>" +
                                  "<hr>" +
                                  "<p>Serveri po punon ne menyre paralele (Multithreading).</p>" +
                                  "</body></html>";
                
                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            });

            server.setExecutor(null); // Perdor ekzekutuesin default
            server.start();
            System.out.println(" HTTP Serveri nisi ne portin 8080 (Shiko: http://localhost:8080/stats)");
            
        } catch (IOException e) {
            System.err.println("Gabim ne HTTP Server: " + e.getMessage());
        }
    }
}