import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashSet;
import java.util.Set;

public class UDPServer {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 5000;
    private static final int MAX_CLIENTS = 3;

  
    private static Set<String> clients = new HashSet<>();

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);

            System.out.println("Serveri po dëgjon në portin: " + PORT);

            while (true) {
                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String clientAddress = packet.getAddress().getHostAddress() + ":" + packet.getPort();

                if (!clients.contains(clientAddress)) {

                    
                    if (clients.size() >= MAX_CLIENTS) {
                        String response = "Serveri i mbingarkuar. Provo më vonë!";
                        byte[] responseData = response.getBytes();

                        DatagramPacket responsePacket = new DatagramPacket(
                                responseData,
                                responseData.length,
                                packet.getAddress(),
                                packet.getPort()
                        );

                        socket.send(responsePacket);
                        System.out.println("Klienti u refuzua: " + clientAddress);
                        continue;
                    }


                    clients.add(clientAddress);
                    System.out.println("Klient i ri u lidh: " + clientAddress);
                }

               
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mesazh nga " + clientAddress + ": " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}