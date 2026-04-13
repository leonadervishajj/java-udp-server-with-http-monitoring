import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try {
           
            DatagramSocket socket = new DatagramSocket(PORT);
            InetAddress ip = InetAddress.getByName(SERVER_IP);

            System.out.println("Serveri u startua!");
            System.out.println("IP: " + ip.getHostAddress());
            System.out.println("Port: " + PORT);

         
            byte[] buffer = new byte[1024];
            System.out.println("Duke dëgjuar për klientët...");

            while (true) {
            
                java.net.DatagramPacket packet = new java.net.DatagramPacket(buffer, buffer.length);
                socket.receive(packet); 
                
                String mesazhi = new String(packet.getData(), 0, packet.getLength());
                System.out.println("U pranua: " + mesazhi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}