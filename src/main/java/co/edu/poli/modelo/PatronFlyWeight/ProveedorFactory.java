package co.edu.poli.modelo.PatronFlyWeight;

import java.util.HashMap;
import java.util.Map;

public class ProveedorFactory {
    private static Map<String, Proveedor> proveedores = new HashMap<>();
    public static Proveedor obtenerProveedor(String nombre){
        if(!proveedores.containsKey(nombre)){
            proveedores.put(nombre, new Proveedor(nombre, "contacto@" + nombre.toLowerCase()+".com"));
        }
        return proveedores.get(nombre);
    }
}
