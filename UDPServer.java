import java.net.*;
import java.io.*;

public class UDPServer {
  
    private static final int PORT = 1234;
    private static final String IP_ADDRESS = "localhost";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Serveri UDP nisi në " + IP_ADDRESS + ":" + PORT);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);

                String clientIP = receivePacket.getAddress().toString() + ":" + receivePacket.getPort();
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();

                System.out.println("Mesazh nga " + clientIP + ": " + message);

              
                MessageLogger.log(clientIP, message);
                TimeoutManager.updateActivity(clientIP);
                
                TimeoutManager.checkTimeouts();
                String responseMessage = "ACK: Mesazhi u mor. Statusi: Aktiv";
                byte[] sendData = responseMessage.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length, 
                    receivePacket.getAddress(), 
                    receivePacket.getPort()
                );
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            System.err.println("Gabim: " + e.getMessage());
            e.printStackTrace();
        }
    }
}