import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.Deque;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Py {

    static Scanner scanner = new Scanner(System.in);

    public static void print(Object dato) {
        System.out.println(dato);
    }

    @SuppressWarnings("unchecked")
    public static <T> T input(String mensaje) {

        System.out.print(mensaje);
        String valor = scanner.nextLine();

        try {
            // INT
            if (valor.matches("-?\\d+")) {
                return (T) Integer.valueOf(valor);
            }

            // FLOAT / DOUBLE
            if (valor.matches("-?\\d+\\.\\d+")) {
                return (T) Float.valueOf(valor);
            }

            // BOOLEAN
            if (valor.equalsIgnoreCase("true") ||
                valor.equalsIgnoreCase("false")) {
                return (T) Boolean.valueOf(valor);
            }
        } catch (Exception e) {
        }
        // STRING
        return (T) valor;
    }

    @SuppressWarnings("unchecked")
    public static <T> T input(String mensaje, Class<T> tipo) {

        System.out.print(mensaje);
        String valor = scanner.nextLine();

        try {
            if (tipo == byte.class || tipo == Byte.class) {
                return (T) Byte.valueOf(valor);
            }
            if (tipo == short.class || tipo == Short.class) {
                return (T) Short.valueOf(valor);
            }
            if (tipo == int.class || tipo == Integer.class) {
                return (T) Integer.valueOf(valor);
            }
            if (tipo == long.class || tipo == Long.class) {
                return (T) Long.valueOf(valor);
            }
            if (tipo == float.class || tipo == Float.class) {
                return (T) Float.valueOf(valor);
            }
            if (tipo == double.class || tipo == Double.class) {
                return (T) Double.valueOf(valor);
            }
            if (tipo == boolean.class || tipo == Boolean.class) {
                return (T) Boolean.valueOf(valor);
            }
            if (tipo == char.class || tipo == Character.class) {
                if (valor.length() >= 1) {
                    return (T) Character.valueOf(valor.charAt(0));
                }
            }
        } catch (NumberFormatException e) {
        }

        return (T) valor;
    }

    public static int len(Object obj) {

        // STRING
        if (obj instanceof String) {
            return ((String) obj).length();
        }

        // LISTAS, SETS, ETC
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).size();
        }

        // HASHMAP
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).size();
        }

        // ARRAYS
        if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }

        return -1;
    }

    public static int[] range(int fin) {
        return range(0, fin, 1);
    }

    public static int[] range(int inicio, int fin) {
        return range(inicio, fin, 1);
    }

    public static int[] range(int inicio, int fin, int paso) {
        int size = (fin - inicio) / paso;

        if ((fin - inicio) % paso != 0) {
            size++;
        }

        int[] numeros = new int[size];

        int valor = inicio;

        for (int i = 0; i < size; i++) {
            numeros[i] = valor;

            valor += paso;
        }
        return numeros;
    }

    public static String type(Object obj) {

        if (obj == null) {
            return "<class 'NoneType'>";
        }

        if (obj instanceof Class<?>) {
            return "<class '" + pythonTypeName((Class<?>) obj) + "'>";
        }

        return "<class '" + pythonTypeName(obj.getClass()) + "'>";
    }

    private static String pythonTypeName(Class<?> tipo) {

        if (tipo.isArray()) {
            return "list";
        }

        if (tipo == byte.class || tipo == Byte.class ||
            tipo == short.class || tipo == Short.class ||
            tipo == int.class || tipo == Integer.class ||
            tipo == long.class || tipo == Long.class ||
            tipo == BigInteger.class) {
            return "int";
        }

        if (tipo == float.class || tipo == Float.class ||
            tipo == double.class || tipo == Double.class ||
            tipo == BigDecimal.class) {
            return "float";
        }

        if (tipo == boolean.class || tipo == Boolean.class) {
            return "bool";
        }

        if (tipo == char.class || tipo == Character.class ||
            tipo == String.class) {
            return "str";
        }

        if (Map.class.isAssignableFrom(tipo)) {
            return "dict";
        }

        if (Set.class.isAssignableFrom(tipo)) {
            return "set";
        }

        if (List.class.isAssignableFrom(tipo) ||
            Queue.class.isAssignableFrom(tipo) ||
            Deque.class.isAssignableFrom(tipo)) {
            return "list";
        }

        if (Collection.class.isAssignableFrom(tipo)) {
            return "collection";
        }

        if (tipo == void.class || tipo == Void.class) {
            return "NoneType";
        }

        if (tipo.isEnum()) {
            return "enum";
        }

        return tipo.getSimpleName();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
    }

    public static <T> String join(String separador, PyList<T> lista) {
        String resultado = "";

        for (int i = 0; i < lista.len(); i++) {
            resultado += lista.get(i);

            if (i != lista.len() - 1) {
                resultado += separador;
            }
        }
        return resultado;
    }

    public static PyList<String> split(String texto, String separador) {
        PyList<String> lista = new PyList<>();
        String[] partes = texto.split(separador);

        for (String parte: partes) {
            lista.append(parte);
        }
        return lista;
    }

    public static void main(String[] args) {
        PyList<String> nombres = split("Otto, Juan, Pedro", ",");

        print(nombres.pyGet(1));
    }

    @SafeVarargs
    public static <T> PyList<T> list(T... elementos) {
        PyList<T> lista = new PyList<>();

        for (T elemento : elementos) {
            lista.append(elemento);
        }
        return lista;
    }

    public static PyString str(String texto) {
        return new PyString(texto);
    }

    public static PyString pystr(String texto) {
        return new PyString(texto);
    }

    public static boolean any(PyList<Boolean> lista) {

        // any() devuelve true si al menos un valor de la lista es true.
        for (Boolean valor : lista) {
            if (valor) {
                return true;
            }
        }

        return false;
    }

    public static boolean all(PyList<Boolean> lista) {

        // all() devuelve true solo si todos los valores son true.
        for (Boolean valor : lista) {
            if (!valor) {
                return false;
            }
        }

        return true;
    }

    public static <A, B> PyList<Pair<A, B>> zip(PyList<A> primera, PyList<B> segunda) {
        PyList<Pair<A, B>> resultado = new PyList<>();

        // Python zip() se detiene cuando termina la lista mas corta.
        int limite = Math.min(primera.len(), segunda.len());

        for (int i = 0; i < limite; i++) {
            resultado.append(
                new Pair<>(primera.get(i), segunda.get(i))
            );
        }

        return resultado;
    }

    public static PyList<Object> flatten(PyList<?> lista) {
        PyList<Object> resultado = new PyList<>();

        for (Object valor : lista) {

            // Si encontramos otra PyList, la aplanamos tambien.
            if (valor instanceof PyList<?>) {
                PyList<Object> subLista = flatten((PyList<?>) valor);

                for (Object subValor : subLista) {
                    resultado.append(subValor);
                }
            } else {
                resultado.append(valor);
            }
        }

        return resultado;
    }
}
