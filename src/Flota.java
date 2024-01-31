import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Flota {
    private Barco[] barcos;     // <<R>>

    public Flota(@NotNull int[] longitudes) {
        barcos=new Barco[longitudes.length];
        for (int i = 0; i < longitudes.length; i++) {
            barcos[i]=new Barco(longitudes[i]);
        }
    }

    public Barco[] getBarcos() {
        return barcos;
    }

    @Override
    public String toString() {
        return "Flota{" +
                "barcos=" + Arrays.toString(barcos) +
                '}';
    }
}
