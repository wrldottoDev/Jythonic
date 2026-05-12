package pythonlike;

public class PyString {

    private String value;

    public PyString(String value) {
        this.value = value;
    }

    public PyString upper() {
        // Igual que Python, no cambiamos el texto original: devolvemos uno nuevo.
        return new PyString(
            value.toUpperCase()
        );
    }

    public PyString lower() {
        // Igual que Python, no cambiamos el texto original: devolvemos uno nuevo.
        return new PyString(
            value.toLowerCase()
        );
    }

    public PyList<String> split() {
        PyList<String> lista = new PyList<>();
        String textoLimpio = value.strip();

        if (textoLimpio.isEmpty()) {
            return lista;
        }

        String[] partes = textoLimpio.split("\\s+");

        for (String parte : partes) {
            lista.append(parte);
        }

        return lista;
    }

    public PyList<String> split(String separador) {
        PyList<String> lista = new PyList<>();

        String[] partes = value.split(separador);

        for (String parte : partes) {
            lista.append(parte);
        }
        return lista;

    }

    public PyString replace(
        String viejo,
        String nuevo
    ) {
        // replace() crea un nuevo PyString con el texto reemplazado.
        return new PyString(
            value.replace(viejo, nuevo)
        );
    }

    public boolean startswith(String texto) {
        return value.startsWith(texto);
    }

    public boolean endswith(String texto) {
        return value.endsWith(texto);
    }

    public PyString strip() {
        // Como Python, strip() quita espacios solo al inicio y al final.
        return new PyString(
            value.strip()
        );
    }

    @Override
    public String toString() {
        return value;
    }

}
