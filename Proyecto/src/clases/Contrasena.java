package clases;

public class Contrasena {
	private static String actual = "default123";

    public static String getActual() {
        return actual;
    }

    public static void setActual(String nueva) {
        actual = nueva;
    }
}
