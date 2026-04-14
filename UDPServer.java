import java.net.*;
import java.io.*;

public class UDPServer {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        // Kjo lejon monitorimin në browser (http://localhost:8080/stats)
        new Thread(new HttpServer()).start();

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("UDP Serveri nisi ne portin " + PORT);
            System.out.println(" Pika 6: Shkruaj 'LISTA' te klienti per te pare fajllat.");

            byte[] buffer = new byte[2048];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);

            
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String clientID = clientAddress.toString() + ":" + clientPort;


                String message = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                System.out.println("Mesazh nga [" + clientID + "]: " + message);
                MessageLogger.log(clientID, message);

       
                TimeoutManager.updateActivity(clientID);
                TimeoutManager.checkTimeouts();
                String responseMessage = ClientHandler.handleRequest(message);

                
                byte[] sendData = responseMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                    sendData, sendData.length, 
                    clientAddress, 
                    clientPort
                );
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            System.err.println(" Gabim ne UDPServer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}