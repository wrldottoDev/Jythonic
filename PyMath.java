public class PyMath {

    public static <T extends Comparable<T>> T max(PyList<T> lista) {

        if (lista.len() == 0) {
            throw new IllegalArgumentException("max() no puede usar una lista vacia");
        }

        T mayor = lista.get(0);

        // Recorremos desde el segundo elemento porque el primero ya es el mayor inicial.
        for (int i = 1; i < lista.len(); i++) {
            T valor = lista.get(i);

            if (valor.compareTo(mayor) > 0) {
                mayor = valor;
            }
        }

        return mayor;
    }

    public static <T extends Comparable<T>> T min(PyList<T> lista) {

        if (lista.len() == 0) {
            throw new IllegalArgumentException("min() no puede usar una lista vacia");
        }

        T menor = lista.get(0);

        // Recorremos desde el segundo elemento porque el primero ya es el menor inicial.
        for (int i = 1; i < lista.len(); i++) {
            T valor = lista.get(i);

            if (valor.compareTo(menor) < 0) {
                menor = valor;
            }
        }

        return menor;
    }

    public static int abs(int numero) {
        return Math.abs(numero);
    }

    public static double sqrt(double numero) {
        return Math.sqrt(numero);
    }

    public static double pow(double base, double exponente) {
        return Math.pow(base, exponente);
    }

    public static int sum(PyList<Integer> lista) {
        int total = 0;

        for (Integer numero : lista) {
            total += numero;
        }

        return total;
    }
}
