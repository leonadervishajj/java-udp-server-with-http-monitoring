 import java.util.Date;

public class ClientHandler {

    public static String handleRequest(String message, String clientAddress) {

        MessageLogger.logMessage(clientAddress + ": " + message);

        if (message.equalsIgnoreCase("ping")) {
            return "pong";
        } 
        else if (message.equalsIgnoreCase("time")) {
            return new Date().toString();
        }
        else if (message.equalsIgnoreCase("hello")) {  
            return "Pershendetje klient!";
        }
        else {
            return "Mesazh i pranuar: " + message;
        }
    }
}