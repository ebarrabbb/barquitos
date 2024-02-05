import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class TableroPropio {
    private static char minFila='A';
    private static char maxFila='J';
    private static int minColumna=1;
    private static int maxColumna=10;

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
        return getMaxFila()-getMinFila()+1;
    }

    private int columnas() {
        return getMaxColumna()-getMinColumna()+1;
    }

    public boolean colocar(@NotNull Barco barco, @NotNull Coordenada c, @NotNull TipoOrientacion o) {
        // 1) La coordenada debe ser válida
        // 2) No puede existir un barco en la misma posición
        // 4) Todos los trozos del barco deben estar colocados dentro del tablero
        Coordenada posicion=c.clonar();
        for (int i = 0; i < barco.longitud(); i++) {
            // 1) La coordenada debe ser válida
            // 2) No puede existir un barco en la misma posición
            // (todas las casillas que va a ocupar el barco son agua)
            if (!esCasillaConAgua(posicion))
                return false;

            if (o.equals(TipoOrientacion.HORIZONTAL)) {
                int columnaNueva=posicion.getColumna()+1;
                if (!esColumna(columnaNueva))
                    return false;
                posicion.setColumna(columnaNueva);
            } else {                                        // VERTICAL
                char filaNueva=(char)(posicion.getFila()+1);
                if (!esFila(filaNueva))
                    return false;
                posicion.setFila(filaNueva);
            }
        }

        if (o.equals(TipoOrientacion.HORIZONTAL)) {
            // TODO: 05/02/2024 Borrar
            System.out.println("HORIZONTAL");
            // Mirar borde superior
            // Calcular la primera casilla del borde superior (arriba a la izquierda)
            Coordenada aux=c.clonar();
            aux.decFila();
            aux.decColumna();
            int i;
            for (i=0;i<barco.longitud()+2 ; i++) {
                if (!esCasillaConAgua(aux))
                    return false;
                aux.incColumna();
            }
            // Mirar borde inferior
            // Calcular la primera casilla del borde inferiro (abajo a la izquierda)
            aux=c.clonar();
            aux.incFila();
            aux.decColumna();
            for (; i < barco.longitud()+2 ; i++) {
                if (!esCasillaConAgua(aux))
                    return false;
                aux.incColumna();
            }
            // Mirar casilla a la izquierda
            aux=c.clonar();
            aux.decColumna();
            if (!esCasillaConAgua(aux))
                return false;
            // Mirar casilla a la derecha
            aux=c.clonar();
            aux.incColumna(barco.longitud());
            if (!esCasillaConAgua(aux))
                return false;
        } else {    // VERTICAL
            // TODO: 05/02/2024 Borrar
            System.out.println("VERTICAL");

            // Mirar borde superior
            // Calcular la primera casilla del borde superior (arriba a la izquierda)
            Coordenada aux=c.clonar();
            aux.decFila();
            aux.decColumna();
            int i;
            for (i=0; i<=2 ; i++) {
                if (!esCasillaConAgua(aux))
                    return false;
                aux.incColumna();
            }
            // Mirar borde inferior
            // Calcular la primera casilla del borde inferiro (abajo a la izquierda)
            aux=c.clonar();
            aux.incFila();
            aux.decColumna();
            for (i=0; i<=2; i++) {
                if (!esCasillaConAgua(aux))
                    return false;
                aux.incColumna();
            }
            // Mirar borde izquierdo
            aux=c.clonar();
            aux.decColumna();
            for (i=0; i<barco.longitud(); i++) {
                if (!esCasillaConAgua(aux))
                    return false;
                aux.incFila();
            }
            // Mirar borde derecho
            aux=c.clonar();
            aux.incColumna();
            for (i=0; i<barco.longitud(); i++) {
                if (!esCasillaConAgua(aux))
                    return false;
                aux.incFila();
            }
        }

        // Colocar barco en la coordenada c y con la orientación o
        Casilla casilla;
        // Coordenada donde hay que colocar el barco
        posicion=new Coordenada(c.getFila(), c.getColumna());
        for (int i = 0; i < barco.longitud(); i++) {
            casilla = getCasilla(posicion);
            casilla.colocarTrozo(barco.getTrozo(i));
            if (o.equals(TipoOrientacion.HORIZONTAL)) {
                char columnaNueva=(char)(posicion.getColumna()+1);
                if (!esColumna(columnaNueva))
                    return false;
                posicion.setColumna(columnaNueva);
            } else {                                                    // VERTICAL
                char filaNueva = (char) (posicion.getFila() + 1);
                if (!esFila(filaNueva))
                    return false;
                posicion.setFila(filaNueva);
            }
        }

        return true;
    }

    private boolean esCasillaConAgua(@NotNull Coordenada aux) {
        return esCoordenada(aux) && getCasilla(aux).esAgua();
    }

    private boolean esCoordenada(@NotNull Coordenada c) {
        return esFila(c.getFila()) && esColumna(c.getColumna());
    }

    public static boolean esFila(char fila) {
        return minFila<=fila && fila<=maxFila;
    }

    public static boolean esColumna(int columa) {
        return minColumna<=columa && columa<=maxColumna;
    }

    public static Coordenada generar() {
        return new Coordenada(  (char) (Math.random()*(maxFila-minFila)+minFila),
                (int) (Math.random()*(maxColumna-minColumna)+minColumna)    );
    }

    private Casilla getCasilla(@NotNull Coordenada c) {
        return casillas[c.getFila()-getMinFila()][c.getColumna()-getMinColumna()];
    }

    public static char getMinFila() {
        return minFila;
    }
    public static char getMaxFila() {
        return maxFila;
    }
    public static int getMinColumna() {
        return minColumna;
    }
    public static int getMaxColumna() {
        return maxColumna;
    }

    private boolean sonAgua(@NotNull Casilla[] casillas) {
        // TODO: 05/02/2024 Borrar
        System.out.println(Arrays.toString(casillas));
        for (Casilla casilla : casillas)
            if (!casilla.esAgua()) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Encabezado con números [columnas]
        sb.append("  ");
        for (int i = getMinColumna(); i < getMaxColumna(); i++) {
            sb.append(String.format(" %d ", i));
        }
        sb.append(" \u2469 ");
        sb.append("\n");

        // Filas
        for (char c = getMinFila(); c <= getMaxFila(); c++) {
            sb.append(String.format("%c ", c));
            for (int j = getMinColumna(); j <= getMaxColumna(); j++) {
                sb.append(casillas[c - getMinFila()][j - getMinColumna()]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
