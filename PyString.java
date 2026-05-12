public class PyString {

    private String valor;

    public PyString(String valor) {
        this.valor = valor;
    }

    public PyString upper() {
        return new PyString(
            valor.toUpperCase()
        );
    }

    public PyString lower() {
        return new PyString(
            valor.toLowerCase()
        );
    }

    public PyList<String> split(String separador) {
        PyList<String> lista = new PyList<>();

        String[] partes = valor.split(separador);

        for (String parte : partes) {
            lista.append(parte);
        }
        return lista;

    }

    public PyString replace(
        String viejo,
        String nuevo
    ) {
        return new PyString(
            valor.replace(viejo, nuevo)
        );
    }

    public boolean startswith(String texto) {
        return valor.startsWith(texto);
    }

    public boolean endswith(String texto) {
        return valor.endsWith(texto);
    }

    public PyString strip() {
        return new PyString(
            valor.replaceAll("\\s+", "")
        );
    }

    @Override
    public String toString() {
        return valor;
    }

}
