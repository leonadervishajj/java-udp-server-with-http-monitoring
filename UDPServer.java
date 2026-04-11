import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer{

    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(PORT);
            InetAddress ip = InetAddress.getByName(SERVER_IP);

            System.out.println("Serveri u startua!");
            System.out.println("IP:" + ip.getHostAddress());
            System.out.print("Port: " + PORT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}