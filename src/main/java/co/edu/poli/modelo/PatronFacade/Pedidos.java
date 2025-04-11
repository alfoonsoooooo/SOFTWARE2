package co.edu.poli.modelo.PatronFacade;

import java.util.ArrayList;
import java.util.List;

public class Pedidos {
    private List<String> pedidos = new ArrayList<>();

    public String agregar(String pedido){
        pedidos.add(pedido);
        return "Tu pedido: " + pedido + "\nHa sido Agregado a tu Lista de Pedidos";
    }

    public String mostrarPedido(){
        if(pedidos.isEmpty()) return "No haz hecho Ningun Pedido";
        return "Historial De Pedidos \n" + String.join("\n", pedidos);
    }
    

}
