import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        new Thread(new HttpServer()).start(); 
        try (DatagramSocket socket = new DatagramSocket(1234)) {
            System.out.println("Serveri u nis ne portin 1234...");
            byte[] buf = new byte[8192];
            while (true) {
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                socket.receive(p);
                String msg = new String(p.getData(), 0, p.getLength()).trim();
                String id = p.getAddress() + ":" + p.getPort();

                if (msg.contains("[ADMIN]")) {
                    System.out.println("Prioritet: Kerkesa nga Admin u procesua.");
                } else {
                    Thread.sleep(500); 
                    System.out.println("User: Kerkesa u procesua me vonese.");
                }

                TimeoutManager.updateActivity(id);
                String response = ClientHandler.handleRequest(msg);
                MessageLogger.log(id, msg);

                byte[] data = response.getBytes();
                socket.send(new DatagramPacket(data, data.length, p.getAddress(), p.getPort()));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}