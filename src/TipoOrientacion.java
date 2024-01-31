public enum TipoOrientacion {
    HORIZONTAL, VERTICAL;

    public static TipoOrientacion generar() {
        return values()[(int)(Math.random()*values().length)];
    }
}
