import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (DatagramSocket s = new DatagramSocket()) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Admin? (po/jo): ");
            String etiketa = sc.nextLine().equalsIgnoreCase("po") ? "[ADMIN] " : "[USER] ";

            while (true) {
                System.out.print("> ");
                String tekst = sc.nextLine();
                if (tekst.equals("exit")) break;

                byte[] b = (etiketa + tekst).getBytes();
                s.send(new DatagramPacket(b, b.length, InetAddress.getByName("127.0.0.1"), 1234));

                byte[] rBuf = new byte[8192];
                DatagramPacket rP = new DatagramPacket(rBuf, rBuf.length);
                s.setSoTimeout(2000);
                try {
                    s.receive(rP);
                    System.out.println("Pergjigja: " + new String(rP.getData(), 0, rP.getLength()));
                } catch (Exception e) { System.out.println("Serveri nuk ktheu pergjigje."); }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}