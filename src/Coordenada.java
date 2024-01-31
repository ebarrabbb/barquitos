public class Coordenada {
    private static char minFila='A';
    private static char maxFila='J';
    private static int minColumna=1;
    private static int maxColumna=10;
    private char fila;                  // [minFila, maxFila]
    private int columna;                // [minColumna, maxColumna]

    public Coordenada(char fila, int columna) {
        setFila(fila);
        setColumna(columna);
    }

    public static Coordenada generar() {
        return new Coordenada(  (char) (Math.random()*(maxFila-minFila)+minFila),
                                (int) (Math.random()*(maxColumna-minColumna)+minColumna)    );
    }

    public char getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
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

    public static boolean esFila(char fila) {
        return minFila<=fila && fila<=maxFila;
    }

    public static boolean esColumna(int columa) {
        return minColumna<=columa && columa<=maxColumna;
    }

    public void setFila(char fila) {
        assert esFila(fila):
                String.format("La fila %c no es válida [%c,%c]", fila, minFila, maxFila);
        this.fila = fila;
    }

    public void setColumna(int columna) {
        assert esColumna(columna):
                String.format("La columna %d no es válida [%d,%d]", columna, minColumna, maxColumna);
        this.columna = columna;
    }

    @Override
    public String toString() {
        return String.format("(%c,%d)", fila, columna);
    }
}
