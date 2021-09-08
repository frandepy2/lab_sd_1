package udp;

/**
 * UDP Client
 * @author FRAND
 */
import java.net.*;
import java.io.IOException;

public class UDPClient {
    public static void main(String[]args){
        try(DatagramSocket aSocket = new DatagramSocket()) {
            byte[]m = args[0].getBytes();
            InetAddress aHost = InetAddress.getByName(args[1]);
            int serverPort = 6789;
            
            DatagramPacket request = new DatagramPacket(m,m.length,aHost,serverPort);
            aSocket.send(request);
            byte[]buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
            aSocket.receive(reply);
            
            System.out.println("Reply : "+new String(reply.getData()));    
        }catch(SocketException e){
            System.out.print(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
