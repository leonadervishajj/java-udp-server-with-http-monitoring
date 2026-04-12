import java.util.Date;

public class ClientHandler {

    public static String handleRequest(String message, String clientAddress) {

      
    if (message.equals("ping")) return "pong";


        if (message.equalsIgnoreCase("ping")) {
            return "pong";
        } 
        else if (message.equalsIgnoreCase("time")) {
            return new Date().toString();
        } 
        else {
            return "Mesazh i pranuar: " + message;
        }
    }
}