# Python-Like for Java

Small educational Java library that recreates some Python-style helpers using plain Java.

The goal is not to replace Java. The goal is to practice:

- classes and objects
- inheritance
- generics
- method overloading
- lambdas
- collections
- API design

All classes currently live in the default Java package, so examples use `Py.print(...)`, `Py.list(...)`, etc. If the project later moves to a named package, static imports can be added.

---

## Compile And Run

```bash
javac *.java
java Main
```

Recommended while developing:

```bash
javac -Xlint:all *.java
```

---

## Main Classes

- `Py`: general Python-like helper functions.
- `PyList<T>`: list class inspired by Python lists.
- `PyString`: immutable Python-like string wrapper.
- `PyMath`: math helpers.
- `PyDict<K, V>`: dictionary-like map.
- `Pair<A, B>`: pair object used by `zip()`.
- `IndexedValue<T>`: index/value object used by `enumerate()`.

---

## Py Helpers

### print()

Prints any value using `System.out.println`.

```java
Py.print("Hello");
Py.print(123);
Py.print(Py.list("Ana", "Luis"));
```

### input()

Reads a line from the console.

```java
String name = Py.input("Name: ");
int age = Py.input("Age: ", Integer.class);
double height = Py.input("Height: ", Double.class);
```

Supported typed input includes `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, `char`, and `String`.

### len()

Returns the length or size of strings, arrays, collections, maps, and `PyList`.

```java
Py.print(Py.len("hello"));
Py.print(Py.len(Py.list(1, 2, 3)));
```

### type()

Returns a Python-style type name.

```java
Py.print(Py.type(10));          // <class 'int'>
Py.print(Py.type("hello"));     // <class 'str'>
Py.print(Py.type(true));        // <class 'bool'>
Py.print(Py.type(Py.list(1)));  // <class 'list'>
```

### range()

Creates an `int[]` that can be used in a `for-each` loop.

```java
for (int i : Py.range(5)) {
    Py.print(i);
}

for (int i : Py.range(2, 10, 2)) {
    Py.print(i);
}
```

### list()

Creates a `PyList` quickly.

```java
PyList<Integer> nums = Py.list(10, 20, 30);
PyList<String> names = Py.list("Ana", "Luis");
```

### join()

Joins a `PyList` into a `String`.

```java
PyList<String> names = Py.list("Ana", "Luis", "Marta");
Py.print(Py.join(", ", names)); // Ana, Luis, Marta
```

### split()

Splits a normal Java `String` and returns `PyList<String>`.

```java
PyList<String> parts = Py.split("Ana,Luis,Marta", ",");
Py.print(parts);
```

### pystr()

Wraps a Java `String` inside `PyString`.

```java
PyString texto = Py.pystr(" hola mundo ");
Py.print(texto.upper());
```

`Py.str(String texto)` also exists and returns a `PyString`.

### any()

Returns `true` if at least one value in a `PyList<Boolean>` is `true`.

```java
PyList<Boolean> values = Py.list(false, false, true);
Py.print(Py.any(values)); // true
```

### all()

Returns `true` only if every value in a `PyList<Boolean>` is `true`.

```java
PyList<Boolean> values = Py.list(true, true, false);
Py.print(Py.all(values)); // false
```

### zip()

Combines two lists into a list of pairs. Like Python, it stops at the shortest list.

```java
PyList<String> names = Py.list("Ana", "Luis", "Marta");
PyList<Integer> ages = Py.list(20, 30);

PyList<Pair<String, Integer>> zipped = Py.zip(names, ages);
Py.print(zipped); // [(Ana, 20), (Luis, 30)]
```

### flatten()

Flattens nested `PyList` objects into one `PyList<Object>`.

```java
PyList<Object> nested = Py.list(
    Py.list(1, 2),
    Py.list(3, Py.list(4, 5))
);

Py.print(Py.flatten(nested)); // [1, 2, 3, 4, 5]
```

### clear()

Prints terminal escape codes that clear many terminals.

```java
Py.clear();
```

---

## PyList

`PyList<T>` extends `ArrayList<T>` and adds Python-inspired methods.

```java
PyList<Integer> nums = new PyList<>();

nums.append(10);
nums.append(20);
nums.append(30);

Py.print(nums); // [10, 20, 30]
```

### append()

Adds an element at the end.

```java
nums.append(40);
```

### pop()

Removes and returns the last element.

```java
Integer last = nums.pop();
```

### len()

Returns the list size.

```java
Py.print(nums.len());
```

### pyGet()

Gets an element by index. Supports negative indexes.

```java
Py.print(nums.pyGet(0));
Py.print(nums.pyGet(-1));
```

### slice()

Returns a new `PyList` with a section of the list.

```java
PyList<Integer> nums = Py.list(10, 20, 30, 40, 50);

Py.print(nums.slice(1, 4)); // [20, 30, 40]
Py.print(nums.slice(3));    // [10, 20, 30]
```

### has()

Checks if a value is inside the list.

```java
Py.print(nums.has(30)); // true
```

### reverse()

Reverses the current list in place.

```java
nums.reverse();
Py.print(nums);
```

### sort()

Sorts the current list in place. Values must implement `Comparable`, such as `Integer`, `Double`, or `String`.

```java
PyList<Integer> nums = Py.list(30, 10, 20);
nums.sort();
Py.print(nums); // [10, 20, 30]
```

### enumerate()

Returns a list of `IndexedValue<T>` objects.

```java
PyList<String> names = Py.list("Ana", "Luis");
PyList<IndexedValue<String>> indexed = PyList.enumerate(names);

for (IndexedValue<String> item : indexed) {
    Py.print(item.index + ": " + item.value);
}
```

### map()

Transforms each element and returns a new list.

```java
PyList<Integer> nums = Py.list(1, 2, 3);
PyList<Integer> doubled = nums.map(n -> n * 2);

Py.print(doubled); // [2, 4, 6]
```

### filter()

Keeps only elements that pass a condition.

```java
PyList<Integer> nums = Py.list(1, 2, 3, 4);
PyList<Integer> evens = nums.filter(n -> n % 2 == 0);

Py.print(evens); // [2, 4]
```

### choice()

Returns one random element from the list.

```java
PyList<String> names = Py.list("Ana", "Luis", "Marta");
Py.print(names.choice());
```

### shuffle()

Randomly reorders the current list in place.

```java
names.shuffle();
Py.print(names);
```

---

## PyString

`PyString` wraps a private Java `String`. Its transformation methods return new `PyString` objects, which makes it feel immutable like Python strings.

```java
PyString texto = Py.pystr(" hola mundo ");

Py.print(texto.upper());
Py.print(texto.strip());
Py.print(texto.replace("hola", "adios"));
Py.print(texto.split());
```

### upper()

Returns a new uppercase `PyString`.

```java
Py.print(Py.pystr("hola").upper()); // HOLA
```

### lower()

Returns a new lowercase `PyString`.

```java
Py.print(Py.pystr("HOLA").lower()); // hola
```

### split()

Without arguments, splits by spaces and returns `PyList<String>`.

```java
Py.print(Py.pystr("hola mundo").split()); // [hola, mundo]
```

With a separator, splits using that separator.

```java
Py.print(Py.pystr("Ana,Luis").split(",")); // [Ana, Luis]
```

### replace()

Returns a new `PyString` with text replaced.

```java
Py.print(Py.pystr("hola mundo").replace("hola", "adios"));
```

### startswith()

Checks whether the text starts with a prefix.

```java
Py.print(Py.pystr("hola").startswith("ho")); // true
```

### endswith()

Checks whether the text ends with a suffix.

```java
Py.print(Py.pystr("hola").endswith("la")); // true
```

### strip()

Returns a new `PyString` without whitespace at the beginning and end.

```java
Py.print(Py.pystr(" hola ").strip()); // hola
```

---

## PyMath

`PyMath` contains Python-inspired math helpers.

### max()

Returns the largest value in a `PyList<T>`. Values must implement `Comparable`.

```java
PyList<Integer> nums = Py.list(10, 30, 20);
Py.print(PyMath.max(nums)); // 30
```

### min()

Returns the smallest value in a `PyList<T>`. Values must implement `Comparable`.

```java
Py.print(PyMath.min(nums)); // 10
```

### abs()

Returns the absolute value of an integer using `Math.abs`.

```java
Py.print(PyMath.abs(-10)); // 10
```

### sqrt()

Returns the square root using `Math.sqrt`.

```java
Py.print(PyMath.sqrt(16)); // 4.0
```

### pow()

Raises a number to a power using `Math.pow`.

```java
Py.print(PyMath.pow(2, 3)); // 8.0
```

### sum()

Returns the total sum of a `PyList<Integer>`.

```java
Py.print(PyMath.sum(Py.list(1, 2, 3))); // 6
```

---

## PyDict

`PyDict<K, V>` extends `HashMap<K, V>` and adds small dictionary-style helpers.

```java
PyDict<String, Integer> ages = new PyDict<>();

ages.set("Ana", 20);
ages.set("Luis", 30);

Py.print(ages.get("Ana"));
Py.print(ages.has("Luis"));
```

### set()

Adds or updates a key/value pair.

```java
ages.set("Marta", 25);
```

### has()

Checks if a key exists.

```java
Py.print(ages.has("Ana")); // true
```

---

## Pair

`Pair<A, B>` stores two values and is used by `Py.zip()`.

```java
Pair<String, Integer> pair = new Pair<>("Ana", 20);

Py.print(pair.first);
Py.print(pair.second);
Py.print(pair); // (Ana, 20)
```

---

## Complete Example

```java
public class Main {

    public static void main(String[] args) {

        PyString texto = Py.pystr(" hola mundo ");

        Py.print(texto.upper());
        Py.print(texto.strip());
        Py.print(texto.replace("hola", "adios"));
        Py.print(texto.split());

        PyList<Integer> nums = Py.list(10, 5, 30, 20);

        nums.sort();
        Py.print(nums);

        Py.print(PyMath.max(nums));
        Py.print(PyMath.min(nums));
        Py.print(PyMath.sum(nums));

        PyList<String> names = Py.list("Ana", "Luis", "Marta");
        Py.print(Py.zip(names, nums));

        PyList<Object> nested = Py.list(
            Py.list(1, 2),
            Py.list(3, Py.list(4, 5))
        );

        Py.print(Py.flatten(nested));
    }
}
```

---

## Notes

- This is a learning project, so implementations are intentionally readable.
- Some methods modify the current object, such as `PyList.reverse()`, `PyList.sort()`, and `PyList.shuffle()`.
- `PyString` methods like `upper()`, `lower()`, `replace()`, and `strip()` return new objects.
- `sort()`, `PyMath.max()`, and `PyMath.min()` need comparable values.
- The project currently uses the default package. For a larger project, move the classes into a named package.

---

## Author

Built by Otoniel Gonzalez as an experiment to make Java feel more Pythonic.
