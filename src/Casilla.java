import org.jetbrains.annotations.NotNull;

public class Casilla {
    private boolean agua;
    private Trozo trozo;        // <<R>>

    public Casilla(boolean agua) {
        if (agua)
            colocarAgua();
    }

    public void colocarAgua() {
        agua=true;
    }

    public void colocarTrozo(@NotNull Trozo trozo) {
        this.agua=false;
        this.trozo=trozo;
    }

    public boolean esAgua() {
        return agua;
    }

    public boolean estaVacia() {
        return !agua && trozo==null;
    }

    public Trozo getTrozo() {
        return trozo;
    }

    @Override
    public String toString() {
        return "|" + (estaVacia() ? " " : (esAgua() ? "\u25CB" : trozo)) + "|";
    }
}
