import java.util.Arrays;

public class Barco {
    private Trozo[] trozos;    // <<R>>

    public Barco(int longitud) {
        assert longitud>=1:
                String.format("La longitud del barco debe ser >=1 (longitud=%d)", longitud);
        trozos=new Trozo[longitud];
        Arrays.fill(trozos, new Trozo());
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
