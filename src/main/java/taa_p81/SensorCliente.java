package taa_p81;
/**
 * UDP Cliente
 * @author FRAND
 */

import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SensorCliente {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        byte[]m;
        System.out.println("Introduce una opcion : ");
        int nrOp=sc.nextInt();
        
        if (nrOp==1){
            //Creamos un JSONObject que tenga un campo operacion y un campo datos que sea otro JSONObject
            JSONObject json = new JSONObject();
            json.put("operacion", "1");
            //Creamos y leemos los datos del objeto sensor
            Sensor s = new Sensor();
            System.out.println("Introduce el id de id_estacion : ");
            s.setId_estacion(sc.nextInt());
            System.out.println("Introduce la ciudad : ");
            s.setCiudad(sc.next());
            System.out.println("Introduce el porcentaje_humedad : ");
            s.setPorcentaje_humedad(sc.nextInt());
            System.out.println("Introduce la temperatura : ");
            s.setTemperatura(sc.nextInt());
            System.out.println("Velocidad del viento :");
            s.setVelocidad_viento(sc.nextInt());
            System.out.println("Fecha DD/MM/AAAA:");
            s.setFecha(sc.next());
            System.out.println("Hora HH:MM:SS:");
            s.setHora(sc.next());

            //Creamos un JSONObject que tenga un campo datos que sea otro JSONObject
            JSONObject json2 = s.toJSON();
            json.put("datos", json2);
            
            //Convertimos a una vector byte
            m = json.toString().getBytes();
        }else if(nrOp==2){
            String ciudad = sc.next();
            JSONObject json = new JSONObject();
            json.put("operacion", "2");
            JSONObject json2 = new JSONObject();
            json2.put("ciudad", ciudad);
            json.put("datos",json2);
            
            m = json.toString().getBytes();
        }else{
            JSONObject json = new JSONObject();
            json.put("operacion", "3");
            
            m = json.toString().getBytes();
        }
        
        try(DatagramSocket aSocket = new DatagramSocket()) {
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;

            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);

            //Recibimos la respuesta del servidor
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            String respuesta = new String(reply.getData());
            System.out.println("Respuesta del servidor: " + respuesta);
                        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
