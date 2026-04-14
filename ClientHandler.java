import java.io.File;
import java.util.Arrays;

public class ClientHandler {

    public static String handleRequest(String message) {
        String request = message.trim().toUpperCase();
        if (request.equals("LISTA")) {
           
            File folder = new File("."); 
            String[] files = folder.list();

            if (files != null && files.length > 0) {
                return "Skedaret ne Server: " + Arrays.toString(files);
            } else {
                return "Serveri nuk ka asnje skedar per te treguar.";
            }
        }

        
        return "ACK: Mesazhi u pranua, por nuk u kerka lista e skedareve.";
    }
}