package co.edu.poli.modelo;

public class AdaptadorNequi implements MetodoPago {
    private Nequi nequi;
    public AdaptadorNequi() {
        this.nequi = new Nequi();
    }
    @Override
    public String realizarPago(int valor) {
        return nequi.realizarTransferencia(valor);
    }
}