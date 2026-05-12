import java.util.ArrayList;

public class PyList<T> extends ArrayList<T> {

    public void append(T valor) {
        this.add(valor);
    } 

    public T pop() {
        T valor = this.get(this.size() - 1);

        this.remove(this.size() - 1);

        return valor;
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
    
}