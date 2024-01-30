public class Trozo {
    private boolean tocado;

    public Trozo() {
        this.tocado=false;
    }

    public boolean estaTocado() {
        return tocado;
    }

    public void tocar() {
        tocado=true;
    }

    @Override
    public String toString() {
        return tocado ? "T" : "\u25A0";
    }
}
