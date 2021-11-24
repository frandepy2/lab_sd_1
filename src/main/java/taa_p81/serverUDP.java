package taa_p81;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * UDP Server
 * @author FRAND
 */
import java.net.*;
import java.util.ArrayList;

public class serverUDP {
    //Array de sensores

    
    //HANDLERS
    public static float ConsultarTemperatura(ArrayList sensores , String ciudad){
        Sensor sensorActual;
        for(int i=0;i<sensores.size();i++){
            sensorActual=(Sensor) sensores.get(i);
            if (sensorActual.getCiudad().compareTo(ciudad)==0){
                return sensorActual.getTemperatura();
            }
        }
        return 0;
    }
    
    public static float ConsultarTemperaturaProm(ArrayList sensores){
        Sensor sensorActual;
        float temp= (float) 0.0;
        int cant=sensores.size();
        for(int i=0;i<sensores.size();i++){
            sensorActual=(Sensor) sensores.get(i);
            temp+=sensorActual.getTemperatura();
        }
        
        return temp/cant;
    }
    
    public static void main(String[]args){
        DatagramSocket aSocket = null;
        //Almacenamos los sensores
        ArrayList<Sensor> sensores = new ArrayList();
        try{
            aSocket = new DatagramSocket(6789);
            byte[]buffer = new byte[1000];
            String mensaje = "Sensor creado correctamente";
            System.out.println("Server on port 6789");
            
            while(true){
                DatagramPacket request = new DatagramPacket(buffer,buffer.length);
                //Recivimos una peticion
                aSocket.receive(request);
                
                //El primer json que recivimos tiene que tener el codigo de operacion
                //Parseamos el String y obtenemos el numero de operacion
                int nroOp;
                JSONParser parser = new JSONParser();
                JSONObject objeto=(JSONObject) parser.parse(request.getData().toString());
                JSONObject datos;
                
                nroOp=Integer.parseInt(objeto.get("operacion").toString());
                
                datos=(JSONObject) parser.parse(objeto.get("datos").toString());
           
                
                //Handler
                if (nroOp == 1 ){ //Recibir los datos de los sensores y agregar al arraylist
                    System.out.println("opcion 1");
                    Sensor sensornuevo = new Sensor(datos);
                    sensores.add(sensornuevo);
                    DatagramPacket reply = new DatagramPacket(mensaje.getBytes(),mensaje.getBytes().length,request.getAddress(),request.getPort());
                    aSocket.send(reply);
                }else if( nroOp == 2){
                    System.out.println("opcion 2");
                    String ciudad = (String) datos.get("ciudad");
                    Float temperatura = ConsultarTemperatura(sensores,ciudad);
                    DatagramPacket reply = new DatagramPacket(temperatura.toString().getBytes(),temperatura.toString().getBytes().length,request.getAddress(),request.getPort());
                    aSocket.send(reply);
                }else if( nroOp == 3){
                    System.out.println("opcion 3");
                    Float temperaturaPromedio = ConsultarTemperaturaProm(sensores);
                    DatagramPacket reply = new DatagramPacket(temperaturaPromedio.toString().getBytes(),temperaturaPromedio.toString().getBytes().length,request.getAddress(),request.getPort());
                    aSocket.send(reply);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
}
