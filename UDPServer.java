import java.net.*;
import java.io.*;

public class UDPServer {
    private static final int PORT = 1234; // Porta ku dëgjon serveri

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDP Serveri u nis në portën " + PORT + "...");
            System.out.println("Duke pritur për mesazhe...");

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                // 1. Pritja e paketës nga klienti (Pika 1)
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                String clientAddress = receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort();

               
                if (ClientManager.registerClient(clientAddress)) {
                    System.out.println(" Mesazhi i pranuar nga [" + clientAddress + "]: " + message);

                 
                    String ackMessage = "ACK: Mesazhi u pranua me sukses!";
                    byte[] sendData = ackMessage.getBytes();

                   
                    DatagramPacket sendPacket = new DatagramPacket(
                        sendData, 
                        sendData.length, 
                        receivePacket.getAddress(), 
                        receivePacket.getPort()
                    );

                    socket.send(sendPacket);
                    System.out.println("📤 ACK u dërgua te klienti.");

                
                } else {
                  
                    System.out.println("Lidhja u refuzua për: " + clientAddress);
                }
            }
        } catch (Exception e) {
            System.err.println("Gabim në Server: " + e.getMessage());
        }
    }
}