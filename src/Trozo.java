public class Trozo {
    private boolean tocado;
    private int n;              // Número del barco

    public Trozo(int n) {
        this.tocado=false;
        this.n=n;
    }

    public boolean estaTocado() {
        return tocado;
    }

    public void tocar() {
        tocado=true;
    }

    @Override
    public String toString() {
        // return tocado ? "T" : "\u25A0";
        return tocado ? "T" : String.valueOf(n);
    }
}
