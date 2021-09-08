package udp;
/**
 * UDP Server
 * @author FRAND
 */
import java.net.*;
import java.io.IOException;

public class UDPServer {
    public static void main(String[]args){
        DatagramSocket aSocket = null;
        try{
            aSocket = new DatagramSocket(6789);
            byte []buffer = new byte[1000];
            System.out.println("Server on port 6789");
            while (true){
                DatagramPacket request = new DatagramPacket(buffer,buffer.length);
                aSocket.receive(request);
                
                System.out.println("Received : "+new String(request.getData()));
                
                /*Se procesa la respueta */
                DatagramPacket reply = new DatagramPacket(request.getData(),request.getLength(),request.getAddress(),request.getPort());
                aSocket.send(reply);
            } 
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
