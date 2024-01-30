import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class TableroPropio {
    private Casilla[][] casillas;
    public TableroPropio() {
        rellenarConAgua();
    }

    private void rellenarConAgua() {
        casillas=new Casilla[filas()][columnas()];
        for (int i = 0; i < filas(); i++) {
            for (int j = 0; j < columnas(); j++) {
                casillas[i][j]=new Casilla(true);
            }
        }
    }

    private int filas() {
        return Coordenada.getMaxFila()-Coordenada.getMinFila()+1;
    }

    private int columnas() {
        return Coordenada.getMaxColumna()-Coordenada.getMinColumna()+1;
    }

    public boolean colocar(@NotNull Barco barco, @NotNull Coordenada c, @NotNull TipoOrientacion o) {
        assert o.equals(TipoOrientacion.HORIZONTAL) || o.equals(TipoOrientacion.VERTICAL):
                String.format("Orientacion desconocida (orientacion=%s)", o);
        // Comprobar si todas las casillas que va a ocupar el barco son agua
        // 4) Todos los trozos del barco deben estar colocados dentro del tablero
        Casilla[] casillasBarco=new Casilla[barco.longitud()];
        Coordenada aux=new Coordenada(c.getFila(), c.getColumna());
        for (int i = 0; i < barco.longitud(); i++) {
            if (esCoordenada(aux))                          // 1) La coordenada debe ser válida
                casillasBarco[i]=getCasilla(aux);
            else 
                return false;
            if (o.equals(TipoOrientacion.HORIZONTAL))
                aux.setFila((char)(aux.getFila()+1));
            else // VERTICAL
                aux.setColumna(aux.getColumna()+1);
        }

        // 2) No puede existir un barco en la misma posición
        if (!sonAgua(casillasBarco)) {
            return false;
        }

        // TODO: 29/01/2024 3) No puede existir un barco contiguo al barco a colocar

        Casilla casilla;
        // Coordenada donde hay que colocar el barco
        Coordenada posicion=new Coordenada(c.getFila(), c.getColumna());
        for (int i = 0; i < barco.longitud(); i++) {
            casilla = getCasilla(posicion);
            casilla.colocarTrozo(barco.getTrozo(i));
            if (o.equals(TipoOrientacion.HORIZONTAL))
                posicion.setColumna(posicion.getColumna()+1);
            else // VERTICAL
                posicion.setFila((char) (posicion.getFila()+1));
        }

        return true;
    }
    
    private boolean esCoordenada(@NotNull Coordenada c) {
        return Coordenada.esFila(c.getFila()) && Coordenada.esColumna(c.getColumna());
    }
    
    private Casilla getCasilla(@NotNull Coordenada c) {
        return casillas[c.getFila()-Coordenada.getMinFila()][c.getColumna()-Coordenada.getMinColumna()];
    }

    private boolean sonAgua(@NotNull Casilla[] casillas) {
        for (Casilla casilla : casillas)
            if (!casilla.esAgua()) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Encabezado con números [columnas]
        sb.append("  ");
        for (int i = Coordenada.getMinColumna(); i < Coordenada.getMaxColumna(); i++) {
            sb.append(String.format(" %d ", i));
        }
        sb.append(" \u2469 ");
        sb.append("\n");

        // Filas
        for (char c = Coordenada.getMinFila(); c <= Coordenada.getMaxFila(); c++) {
            sb.append(String.format("%c ", c));
            for (int j = Coordenada.getMinColumna(); j <= Coordenada.getMaxColumna(); j++) {
                sb.append(casillas[c - Coordenada.getMinFila()][j - Coordenada.getMinColumna()]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
