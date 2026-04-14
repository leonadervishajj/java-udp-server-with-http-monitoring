import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

public class TimeoutManager {
    private static ConcurrentHashMap<String, Long> activeClients = new ConcurrentHashMap<>();
    
    private static final long TIMEOUT_LIMIT = 60000; 

    public static void updateActivity(String clientIP) {
        if (!activeClients.containsKey(clientIP)) {
            System.out.println("Klienti u rilidh/u shtua: " + clientIP);
        }
        activeClients.put(clientIP, System.currentTimeMillis());
    }

   
    public static void checkTimeouts() {
        long currentTime = System.currentTimeMillis();
        
        activeClients.entrySet().removeIf(entry -> {
            boolean isInactive = (currentTime - entry.getValue()) > TIMEOUT_LIMIT;
            if (isInactive) {
                System.out.println(" Lidhja u mbyll për shkak të inaktivitetit: " + entry.getKey());
            }
            return isInactive;
        });
    }

    public static int getActiveCount() {
        return activeClients.size();
    }
}