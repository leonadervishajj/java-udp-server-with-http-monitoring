import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageLogger {
    private static final String FILE_NAME = "server_logs.txt";

    public static void log(String clientAddress, String message) {
      
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String now = dtf.format(LocalDateTime.now());
        String logEntry = String.format("[%s] Klienti: %s | Mesazhi: %s", now, clientAddress, message);


        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            out.println(logEntry);
            System.out.println("📝 Mesazhi u ruajt ne log.");
        } catch (IOException e) {
            System.err.println("Gabim gjate shkrimit ne log: " + e.getMessage());
        }
    }
}