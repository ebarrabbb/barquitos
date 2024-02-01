public class Coordenada {
    private char fila;                  // [minFila, maxFila]
    private int columna;                // [minColumna, maxColumna]

    public Coordenada(char fila, int columna) {
        setFila(fila);
        setColumna(columna);
    }

    public Coordenada clonar() {
        return new Coordenada(fila, columna);
    }

    public char getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void decFila() {
        setFila((char)(fila-1));
    }
    public void decFila(int n) {
        setFila((char)(fila-n));
    }

    public void incFila() {
        setFila((char)(fila+1));
    }
    public void incFila(int n) {
        setFila((char)(fila+n));
    }

    public void decColumna() {
        setColumna(columna-1);
    }

    public void decColumna(int n) {
        setColumna(columna-n);
    }

    public void incColumna() {
        setColumna(columna+1);
    }

    public void incColumna(int n) {
        setColumna(columna+n);
    }

    public void setFila(char fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return String.format("(%c,%d)", fila, columna);
    }
}
