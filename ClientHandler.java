import java.io.*;
import java.util.*;
import java.nio.file.*;

public class ClientHandler {
    public static String handleRequest(String message) {
        boolean eshteAdmin = message.contains("[ADMIN]");
        String mesazhiPaEtikete = message.replace("[ADMIN]", "").replace("[USER]", "").trim();
        
        String[] fjala = mesazhiPaEtikete.split(" ", 2);
        String komanda = fjala[0].toLowerCase();
        String emriFajllit = (fjala.length > 1) ? fjala[1] : "";

        try {
            switch (komanda) {
                case "/list":
                    File f = new File(".");
                    return "Lista e skedareve: " + Arrays.toString(f.list());
                case "/read":
                    if (emriFajllit.isEmpty()) return "Gabim: Shkruaj emrin e fajllit!";
                    return "Permbajtja: " + new String(Files.readAllBytes(Paths.get(emriFajllit)));
                case "/delete":
                    if (!eshteAdmin) return "Gabim: Nuk keni privilegje per fshirje!";
                    File perFshirje = new File(emriFajllit);
                    return (perFshirje.exists() && perFshirje.delete()) ? "Sukses: U fshi." : "Gabim: Deshtoi fshirja.";
                default:
                    return "Komandat: /list, /read, /delete";
            }
        } catch (Exception e) { return "Gabim: " + e.getMessage(); }
    }
}