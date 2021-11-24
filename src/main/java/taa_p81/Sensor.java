package taa_p81;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * Modelo del objeto a pedir
 * @author FRAND
 */
public class Sensor{
    int id_estacion;
    String ciudad;
    int porcentaje_humedad;
    float temperatura;
    int velocidad_viento;
    String fecha; // Recibimos en formato 
    String hora;

    public Sensor(int id_estacion, String ciudad, int porcentaje_humedad, float temperatura, int velocidad_viento, String fecha, String hora) {
        this.id_estacion = id_estacion;
        this.ciudad = ciudad;
        this.porcentaje_humedad = porcentaje_humedad;
        this.temperatura = temperatura;
        this.velocidad_viento = velocidad_viento;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Sensor() {
    }
    
    //Recibimos un JSONObject y lo transformamos en un objeto Sensor
    public Sensor(JSONObject objetoJSON){
        JSONParser parser = new JSONParser();
        try{
            JSONObject objeto = (JSONObject) parser.parse(objetoJSON.toString());
            this.id_estacion = Integer.parseInt(objeto.get("id_estacion").toString());
            this.ciudad = objeto.get("ciudad").toString();
            this.porcentaje_humedad = Integer.parseInt(objeto.get("porcentaje_humedad").toString());
            this.temperatura = Float.parseFloat(objeto.get("temperatura").toString());
            this.velocidad_viento = Integer.parseInt(objeto.get("velocidad_viento").toString());
            this.fecha = objeto.get("fecha").toString();
            this.hora = objeto.get("hora").toString();
        }catch(Exception e){
            System.out.println("Error al parsear el JSONObject");
        }
    }
    
    //Constructor que recibe un String con el JSONObject lo parsea y lo transforma en un objeto Sensor
    public Sensor(String json){
        JSONParser parser = new JSONParser();
        try{
            JSONObject objeto = (JSONObject) parser.parse(json);
            this.id_estacion = Integer.parseInt(objeto.get("id_estacion").toString());
            this.ciudad = objeto.get("ciudad").toString();
            this.porcentaje_humedad = Integer.parseInt(objeto.get("porcentaje_humedad").toString());
            this.temperatura = Float.parseFloat(objeto.get("temperatura").toString());
            this.velocidad_viento = Integer.parseInt(objeto.get("velocidad_viento").toString());
            this.fecha = objeto.get("fecha").toString();
            this.hora = objeto.get("hora").toString();
        }catch(Exception e){
            System.out.println("Error al parsear el JSONObject");
        }
    }

    public int getId_estacion() {
        return id_estacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getPorcentaje_humedad() {
        return porcentaje_humedad;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public int getVelocidad_viento() {
        return velocidad_viento;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setId_estacion(int id_estacion) {
        this.id_estacion = id_estacion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setPorcentaje_humedad(int porcentaje_humedad) {
        this.porcentaje_humedad = porcentaje_humedad;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setVelocidad_viento(int velocidad_viento) {
        this.velocidad_viento = velocidad_viento;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    //Metodo para devolver el objeto en formato JSON
    public JSONObject toJSON(){
        JSONObject objetoJSON = new JSONObject();
        objetoJSON.put("id_estacion", this.id_estacion);
        objetoJSON.put("ciudad", this.ciudad);
        objetoJSON.put("porcentaje_humedad", this.porcentaje_humedad);
        objetoJSON.put("temperatura", this.temperatura);
        objetoJSON.put("velocidad_viento", this.velocidad_viento);
        objetoJSON.put("fecha", this.fecha);
        objetoJSON.put("hora", this.hora);
        return objetoJSON;
    }
    
}
