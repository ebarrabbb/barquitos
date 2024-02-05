import java.util.Arrays;

public class Barco {
    private Trozo[] trozos;     // <<R>>
    private int n;              // Número de barco en una flota

    // TODO: 05/02/2024 n Número de barco en una flota
    public Barco(int longitud, int n) {
        assert longitud>=1:
                String.format("La longitud del barco debe ser >=1 (longitud=%d)", longitud);
        trozos=new Trozo[longitud];
        Arrays.fill(trozos, new Trozo(n));
    }

    public Trozo getTrozo(int i) {
        assert i>=0 && i<trozos.length:
                String.format("El trozo %d no existe (longitud=%d)", i, trozos.length);
        return trozos[i];
    }

    public int longitud() {
        return trozos.length;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (Trozo trozo : trozos) {
            sb.append(trozo);
        }
        return sb.toString();
    }
}
