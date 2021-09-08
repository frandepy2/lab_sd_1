/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.net.*;
import java.io.*;


public class TCPServer {
    public static void main(String[]args){
        try{
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Server on port "+serverPort);
            while (true){
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class Connection extends Thread{
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    
    public Connection(Socket aClientSocket){
        try{
            clientSocket= aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void run(){
        try{
            String data = in.readUTF();
            System.out.println("Received :"+data);
            
            out.writeUTF(data+" cliente");
            System.out.println("Response :"+data+" cliente");
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}