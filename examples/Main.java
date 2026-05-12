
import pythonlike.*;
import static pythonlike.Py.*;

public class Main {

    public static void main(String[] args) {

        PyString texto = pystr(" hola mundo ");

        print(texto.upper());
        print(texto.strip());
        print(texto.replace("hola", "adios"));
        print(texto.split());

        PyList<Integer> numeros = list(10, 5, 30, 20);

        print(PyMath.max(numeros));
        print(PyMath.min(numeros));
        print(PyMath.sum(numeros));
        print(PyMath.abs(-10));
        print(PyMath.sqrt(16));
        print(PyMath.pow(2, 3));

        PyList<Boolean> condiciones = list(true, false, true);

        print(any(condiciones));
        print(all(condiciones));

        PyList<String> nombres = list("Ana", "Luis", "Marta");
        print(zip(nombres, numeros));

        PyList<Object> anidada = list(
            list(1, 2),
            list(3, list(4, 5))
        );
        print(flatten(anidada));

        print(nombres.choice());
        nombres.shuffle();
        print(nombres);
    }
}
