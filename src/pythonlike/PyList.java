package pythonlike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

public class PyList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    private static final Random RANDOM = new Random();

    public void append(T valor) {
        this.add(valor);
    } 

    public T pop() {
        T valor = this.get(this.size() - 1);

        this.remove(this.size() - 1);

        return valor;
    }

    public int len() {
        return this.size();
    }

    public PyList<T> slice(int inicio, int fin) {

        if (inicio < 0) {
            inicio = this.size() + inicio;
        }

        if (fin < 0) {
            fin = this.size() + fin;
        }

        PyList<T> nuevaLista = new PyList<>();

        for (int i = inicio; i < fin; i++) {

            nuevaLista.append(this.get(i));

        }

        return nuevaLista;
    }

    public PyList<T> slice(int fin) {
        return slice(0, fin);
    }

    public T pyGet(int indice) {

        if (indice < 0) {
            indice = this.size() + indice;
        }

        return this.get(indice);
    }

    public boolean has(T valor) {
        return this.contains(valor);
    }

    public void reverse() {

        int inicio = 0;

        int fin = this.size() - 1;

        while (inicio < fin) {

            T temporal = this.get(inicio);

            this.set(inicio, this.get(fin));

            this.set(fin, temporal);

            inicio++;

            fin--;
        }
    }

    public void sort() {

        for (int i = 0; i < this.size() - 1; i++) {

            for (int j = 0; j < this.size() - 1 - i; j++) {

                T actual = this.get(j);

                T siguiente = this.get(j + 1);

                if (compare(actual, siguiente) > 0) {

                    T temporal = this.get(j);

                    this.set(j, this.get(j + 1));

                    this.set(j + 1, temporal);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private int compare(T actual, T siguiente) {

        if (!(actual instanceof Comparable<?>)) {
            throw new IllegalArgumentException("Los valores no se pueden ordenar");
        }

        return ((Comparable<T>) actual).compareTo(siguiente);
    }

    public static <T> PyList<IndexedValue<T>> enumerate(PyList<T> lista) {

        PyList<IndexedValue<T>> resultado = new PyList<>();

        for (int i = 0; i < lista.len(); i++) {

            resultado.append(

                new IndexedValue<>(i, lista.get(i))

            );

        }

        return resultado;

    }

    public <R> PyList<R> map(Function<T, R> funcion) {
        PyList<R> nuevaLista = new PyList<>();

        for (T valor: this) {
            nuevaLista.append(
                funcion.apply(valor)
            );
        }
        return nuevaLista;
    }

    public PyList<T> filter(Predicate<T> condicion) {

        PyList<T> nuevaLista = new PyList<>();

        for (T valor : this) {

            if (condicion.test(valor)) {
                nuevaLista.append(valor);
            }
        }

        return nuevaLista;
    }

    public T choice() {

        if (this.len() == 0) {
            throw new IllegalArgumentException("choice() no puede usar una lista vacia");
        }

        // Elegimos un indice aleatorio valido para esta lista.
        int indiceAleatorio = RANDOM.nextInt(this.len());

        return this.get(indiceAleatorio);
    }

    public void shuffle() {
        // Collections.shuffle mezcla la lista actual en el mismo objeto.
        Collections.shuffle(this);
    }
    
}
