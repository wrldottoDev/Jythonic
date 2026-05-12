import java.util.HashMap;

public class PyDict<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 1L;

    public void set(K key, V value) {
        this.put(key, value);
    }

    public boolean has(K key) {
        return this.containsKey(key);
    }

}
