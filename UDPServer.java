import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 5000;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
       
            DatagramSocket socket = new DatagramSocket(PORT);
            InetAddress ip = InetAddress.getByName(SERVER_IP);
            
            System.out.println("Serveri UDP startoi ne " + SERVER_IP + ":" + PORT);

            byte[] receiveData = new byte[BUFFER_SIZE];

            while (true) {
        
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String clientAddress = receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort();
                
                if (ClientManager.registerClient(clientAddress)) {
                    System.out.println("Lidhje e re e pranuar: " + clientAddress);
                } else {
                    System.out.println("Lidhja u refuzua per: " + clientAddress + " (Limiti u arrit)");
                }

                receiveData = new byte[BUFFER_SIZE];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}