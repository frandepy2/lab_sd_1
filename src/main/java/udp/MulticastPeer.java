package udp;

import java.net.*;
import java.util.Scanner;

public class MulticastPeer {
    public static void main(String[]args){
        MulticastSocket s = null;
        Scanner sc = new Scanner(System.in);
        String mensaje = sc.next();
        try{
            InetAddress group = InetAddress.getByName("192.168.100.255");
            s = new MulticastSocket(6789);
            s.joinGroup(group);
            
            byte[]m =mensaje.getBytes();
            
            DatagramPacket messageOut = new DatagramPacket(m,m.length,group,6789);
            s.send(messageOut);
            
            byte []buffer = new byte[1000];
            for(int i = 0;i<3;i++){
                DatagramPacket messageIn = new DatagramPacket(buffer,buffer.length);
                s.receive(messageIn);
                System.out.println("Received :"+new String(messageIn.getData()));
            }
            
            s.leaveGroup(group);
            
        }catch(Exception e){
            System.out.println("Error :"+e.getMessage());
        }finally{
            if (s !=null) s.close();
        }
    }
}
