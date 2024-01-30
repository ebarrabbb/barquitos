/**
 * Barquitos
 * @author Eduardo Barra Balao
 * @version 0.8.4
 */
public class Principal {
    public static void main(String[] args) {
        System.out.println("Barquitos 0.8.4");
        // System.out.println(new Partida(new String[]{"PEDRO", "ZULEMA"}));
        TableroPropio tp = new TableroPropio();
        System.out.print(tp);
        tp.colocar(new Barco(4), new Coordenada('A', 1), TipoOrientacion.VERTICAL);
        tp.colocar(new Barco(3), new Coordenada('B', 4), TipoOrientacion.HORIZONTAL);
        // Error: 1) La coordenada debe ser válida
        Coordenada c = new Coordenada('K', 4);
        if (!tp.colocar(new Barco(3), c, TipoOrientacion.HORIZONTAL))
            System.out.println("1) Coordenada no válida: "+c);
        // Error: 2) No puede existir un barco en la misma posición
        if (!tp.colocar(new Barco(3), new Coordenada('A', 4), TipoOrientacion.HORIZONTAL))
            System.out.println("2) Existe un barco en la misma posición");
        // 4) Todos los trozos del barco deben estar colocados dentro del tablero
        if (!tp.colocar(new Barco(3), new Coordenada('I', 1 ), TipoOrientacion.HORIZONTAL))
            System.out.println("4) Todos los trozos del barco deben estar colocados dentro del tablero");
        System.out.print(tp);
    }
}