package co.edu.poli.modelo;

public class Proveedor {
    private static final String NOMBRE = "SERVIENTREGA";
    private final Evaluacion evaluacion;
    private final PoliticaEntrega politicaEntrega;
    private final Certificacion certificacion;
    
    private Proveedor(Builder builder) {
        this.evaluacion = builder.evaluacion;
        this.politicaEntrega = builder.politicaEntrega;
        this.certificacion = builder.certificacion;
    }
    public String mostrarInfo() {
        String info = "Proveedor: " + NOMBRE + "\n" +
                      "Evaluación: " + (evaluacion != null ? evaluacion.getEvaluacion() : "Evaluación sin Definir") + "\n" +
                      "Certificación: " + (certificacion != null ? certificacion.getTipo() : "Sin Certificación Contratada") + "\n" +
                      "Política de Entrega: " + (politicaEntrega != null ? politicaEntrega.getTiempoEntrega() : "Política de Entrega sin Pactar");
        
        return info;
    }
    public static class Builder{
        private Evaluacion evaluacion;
        private PoliticaEntrega politicaEntrega;
        private Certificacion certificacion;
        public Builder evaluacion(Evaluacion evaluacion){
            this.evaluacion = evaluacion;
            return this;
        }
        public Builder politicaEntrega(PoliticaEntrega politicaEntrega){
            this.politicaEntrega = politicaEntrega;
            return this;
        }
        public Builder certificacion(Certificacion certificacion){
            this.certificacion = certificacion;
            return this;
        }
        public Proveedor build(){
            return new Proveedor(this);
        }
    }
}