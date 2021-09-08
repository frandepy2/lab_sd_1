/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.net.*;
import java.io.*;
import java.util.Scanner;


/**
 * @author FRAND
 */
public class TCPClient {
    public static void main(String[] args){
        Socket s = null;
        String mensaje;
        Scanner sc =new Scanner(System.in);
        mensaje=sc.next();
        try{
            int serverPort = 6969;
            s = new Socket("26.54.243.204",serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            out.writeUTF(mensaje);
            System.out.println("sended :"+mensaje);
            String data = in.readUTF();
            
            System.out.println("Received : "+data);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

