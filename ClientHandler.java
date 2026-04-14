import java.io.File;
import java.util.Arrays;

public class ClientHandler {
    public static String handleRequest(String message) {
        String request = message.trim().toUpperCase();

        if (request.equals("LISTA")) {
            File folder = new File("."); 
            String[] files = folder.list();
            
            if (files != null) {
                return "Skedarët në server: " + Arrays.toString(files);
            } else {
                return " Nuk u gjet asnjë skedar.";
            }
        }
        
        return "ACK: Mesazhi u pranua.";
    }
}