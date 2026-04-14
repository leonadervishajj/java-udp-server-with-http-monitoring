import java.util.*;
import java.util.concurrent.*;

public class ClientManager {
    private static final int MAX_CLIENTS = 10; 

    private static Map<String, Long> activeClients = new ConcurrentHashMap<>();
    public static boolean registerClient(String clientAddress) {
        
       
        if (!activeClients.containsKey(clientAddress) && activeClients.size() >= MAX_CLIENTS) {
            System.out.println("Limiti prej " + MAX_CLIENTS + " anëtarësh u arrit! Refuzohet: " + clientAddress);
            return false; 
        }
        activeClients.put(clientAddress, System.currentTimeMillis());
        
        System.out.println("Klienti u regjistrua: " + clientAddress + " (Total: " + activeClients.size() + ")");
        return true;
    }

    public static Map<String, Long> getActiveClientsMap() {
        return activeClients;
    }
}


