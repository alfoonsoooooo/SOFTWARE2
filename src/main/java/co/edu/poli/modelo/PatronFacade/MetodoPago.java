package co.edu.poli.modelo.PatronFacade;

import java.util.HashMap;
import java.util.Map;

public class MetodoPago {
    private Map<String, String> metodospago = new HashMap<>();

    public MetodoPago() {
        metodospago.put("Nequi", "Activado");
        metodospago.put("DaviPlata", "Activado");
        metodospago.put("Tarjeta de Credito", "Desactivado");
        metodospago.put("Tarjeta de Debito", "Activado");
    }

    public String activarMetodo(String metodo){
        for(String metodos: metodospago.keySet()){
            if(metodos.equalsIgnoreCase(metodo)){
                metodospago.put(metodos, "Activado");
                return "Tu metodo de Pago: " + metodo + " Fue Activado";
            }
        }
        return "Metodo de Pago no Soportado";
        }

    public String bloquearMetodo(String metodo){
        for(String metodos: metodospago.keySet()){
            if(metodos.equalsIgnoreCase(metodo)){
                metodospago.put(metodos, "Desactivado");
                return "Tu metodo de Pago: " + metodo + " Fue Desactivado";
            }
        }
        return "Metodo de Pago no Registrado";
    }

    public String mostrar(){
        String listar = "Metodos De Pago Configurados:";
        for(String metodo: metodospago.keySet()){
            listar += "\n Metodo: " + metodo + " - Estado: " + metodospago.get(metodo);
        }
        return listar;
    }
    
}
