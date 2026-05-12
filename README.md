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

The library now lives in the `pythonlike` package. That means future programs can import it and use Python-like helpers such as `print(...)`, `list(...)`, `len(...)`, and `pystr(...)`.

---

## Compile And Run

Compile the library and the example:

```bash
javac -d out src/pythonlike/*.java examples/Main.java
java -cp out Main
```

Recommended while developing:

```bash
javac -Xlint:all -d out src/pythonlike/*.java examples/Main.java
```

Create a reusable JAR:

```bash
javac -d out src/pythonlike/*.java
jar cf python-like.jar -C out .
```

Use the JAR from another project:

```bash
javac -cp python-like.jar Main.java
java -cp .:python-like.jar Main
```

On Windows, use `;` instead of `:`:

```bash
java -cp .;python-like.jar Main
```

Every program that uses the library should import it like this:

```java
import pythonlike.*;
import static pythonlike.Py.*;
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
print("Hello");
print(123);
print(list("Ana", "Luis"));
```

### input()

Reads a line from the console.

```java
String name = input("Name: ");
int age = input("Age: ", Integer.class);
double height = input("Height: ", Double.class);
```

Supported typed input includes `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, `char`, and `String`.

### len()

Returns the length or size of strings, arrays, collections, maps, and `PyList`.

```java
print(len("hello"));
print(len(list(1, 2, 3)));
```

### type()

Returns a Python-style type name.

```java
print(type(10));          // <class 'int'>
print(type("hello"));     // <class 'str'>
print(type(true));        // <class 'bool'>
print(type(list(1)));  // <class 'list'>
```

### range()

Creates an `int[]` that can be used in a `for-each` loop.

```java
for (int i : range(5)) {
    print(i);
}

for (int i : range(2, 10, 2)) {
    print(i);
}
```

### list()

Creates a `PyList` quickly.

```java
PyList<Integer> nums = list(10, 20, 30);
PyList<String> names = list("Ana", "Luis");
```

### join()

Joins a `PyList` into a `String`.

```java
PyList<String> names = list("Ana", "Luis", "Marta");
print(join(", ", names)); // Ana, Luis, Marta
```

### split()

Splits a normal Java `String` and returns `PyList<String>`.

```java
PyList<String> parts = split("Ana,Luis,Marta", ",");
print(parts);
```

### pystr()

Wraps a Java `String` inside `PyString`.

```java
PyString texto = pystr(" hola mundo ");
print(texto.upper());
```

`str(String texto)` also exists and returns a `PyString`.

### any()

Returns `true` if at least one value in a `PyList<Boolean>` is `true`.

```java
PyList<Boolean> values = list(false, false, true);
print(any(values)); // true
```

### all()

Returns `true` only if every value in a `PyList<Boolean>` is `true`.

```java
PyList<Boolean> values = list(true, true, false);
print(all(values)); // false
```

### zip()

Combines two lists into a list of pairs. Like Python, it stops at the shortest list.

```java
PyList<String> names = list("Ana", "Luis", "Marta");
PyList<Integer> ages = list(20, 30);

PyList<Pair<String, Integer>> zipped = zip(names, ages);
print(zipped); // [(Ana, 20), (Luis, 30)]
```

### flatten()

Flattens nested `PyList` objects into one `PyList<Object>`.

```java
PyList<Object> nested = list(
    list(1, 2),
    list(3, list(4, 5))
);

print(flatten(nested)); // [1, 2, 3, 4, 5]
```

### clear()

Prints terminal escape codes that clear many terminals.

```java
clear();
```

---

## PyList

`PyList<T>` extends `ArrayList<T>` and adds Python-inspired methods.

```java
PyList<Integer> nums = new PyList<>();

nums.append(10);
nums.append(20);
nums.append(30);

print(nums); // [10, 20, 30]
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
print(nums.len());
```

### pyGet()

Gets an element by index. Supports negative indexes.

```java
print(nums.pyGet(0));
print(nums.pyGet(-1));
```

### slice()

Returns a new `PyList` with a section of the list.

```java
PyList<Integer> nums = list(10, 20, 30, 40, 50);

print(nums.slice(1, 4)); // [20, 30, 40]
print(nums.slice(3));    // [10, 20, 30]
```

### has()

Checks if a value is inside the list.

```java
print(nums.has(30)); // true
```

### reverse()

Reverses the current list in place.

```java
nums.reverse();
print(nums);
```

### sort()

Sorts the current list in place. Values must implement `Comparable`, such as `Integer`, `Double`, or `String`.

```java
PyList<Integer> nums = list(30, 10, 20);
nums.sort();
print(nums); // [10, 20, 30]
```

### enumerate()

Returns a list of `IndexedValue<T>` objects.

```java
PyList<String> names = list("Ana", "Luis");
PyList<IndexedValue<String>> indexed = PyList.enumerate(names);

for (IndexedValue<String> item : indexed) {
    print(item.index + ": " + item.value);
}
```

### map()

Transforms each element and returns a new list.

```java
PyList<Integer> nums = list(1, 2, 3);
PyList<Integer> doubled = nums.map(n -> n * 2);

print(doubled); // [2, 4, 6]
```

### filter()

Keeps only elements that pass a condition.

```java
PyList<Integer> nums = list(1, 2, 3, 4);
PyList<Integer> evens = nums.filter(n -> n % 2 == 0);

print(evens); // [2, 4]
```

### choice()

Returns one random element from the list.

```java
PyList<String> names = list("Ana", "Luis", "Marta");
print(names.choice());
```

### shuffle()

Randomly reorders the current list in place.

```java
names.shuffle();
print(names);
```

---

## PyString

`PyString` wraps a private Java `String`. Its transformation methods return new `PyString` objects, which makes it feel immutable like Python strings.

```java
PyString texto = pystr(" hola mundo ");

print(texto.upper());
print(texto.strip());
print(texto.replace("hola", "adios"));
print(texto.split());
```

### upper()

Returns a new uppercase `PyString`.

```java
print(pystr("hola").upper()); // HOLA
```

### lower()

Returns a new lowercase `PyString`.

```java
print(pystr("HOLA").lower()); // hola
```

### split()

Without arguments, splits by spaces and returns `PyList<String>`.

```java
print(pystr("hola mundo").split()); // [hola, mundo]
```

With a separator, splits using that separator.

```java
print(pystr("Ana,Luis").split(",")); // [Ana, Luis]
```

### replace()

Returns a new `PyString` with text replaced.

```java
print(pystr("hola mundo").replace("hola", "adios"));
```

### startswith()

Checks whether the text starts with a prefix.

```java
print(pystr("hola").startswith("ho")); // true
```

### endswith()

Checks whether the text ends with a suffix.

```java
print(pystr("hola").endswith("la")); // true
```

### strip()

Returns a new `PyString` without whitespace at the beginning and end.

```java
print(pystr(" hola ").strip()); // hola
```

---

## PyMath

`PyMath` contains Python-inspired math helpers.

### max()

Returns the largest value in a `PyList<T>`. Values must implement `Comparable`.

```java
PyList<Integer> nums = list(10, 30, 20);
print(PyMath.max(nums)); // 30
```

### min()

Returns the smallest value in a `PyList<T>`. Values must implement `Comparable`.

```java
print(PyMath.min(nums)); // 10
```

### abs()

Returns the absolute value of an integer using `Math.abs`.

```java
print(PyMath.abs(-10)); // 10
```

### sqrt()

Returns the square root using `Math.sqrt`.

```java
print(PyMath.sqrt(16)); // 4.0
```

### pow()

Raises a number to a power using `Math.pow`.

```java
print(PyMath.pow(2, 3)); // 8.0
```

### sum()

Returns the total sum of a `PyList<Integer>`.

```java
print(PyMath.sum(list(1, 2, 3))); // 6
```

---

## PyDict

`PyDict<K, V>` extends `HashMap<K, V>` and adds small dictionary-style helpers.

```java
PyDict<String, Integer> ages = new PyDict<>();

ages.set("Ana", 20);
ages.set("Luis", 30);

print(ages.get("Ana"));
print(ages.has("Luis"));
```

### set()

Adds or updates a key/value pair.

```java
ages.set("Marta", 25);
```

### has()

Checks if a key exists.

```java
print(ages.has("Ana")); // true
```

---

## Pair

`Pair<A, B>` stores two values and is used by `zip()`.

```java
Pair<String, Integer> pair = new Pair<>("Ana", 20);

print(pair.first);
print(pair.second);
print(pair); // (Ana, 20)
```

---

## Complete Example

```java
import pythonlike.*;
import static pythonlike.Py.*;

public class Main {

    public static void main(String[] args) {

        PyString texto = pystr(" hola mundo ");

        print(texto.upper());
        print(texto.strip());
        print(texto.replace("hola", "adios"));
        print(texto.split());

        PyList<Integer> nums = list(10, 5, 30, 20);

        nums.sort();
        print(nums);

        print(PyMath.max(nums));
        print(PyMath.min(nums));
        print(PyMath.sum(nums));

        PyList<String> names = list("Ana", "Luis", "Marta");
        print(zip(names, nums));

        PyList<Object> nested = list(
            list(1, 2),
            list(3, list(4, 5))
        );

        print(flatten(nested));
    }
}
```

---

## Notes

- This is a learning project, so implementations are intentionally readable.
- Some methods modify the current object, such as `PyList.reverse()`, `PyList.sort()`, and `PyList.shuffle()`.
- `PyString` methods like `upper()`, `lower()`, `replace()`, and `strip()` return new objects.
- `sort()`, `PyMath.max()`, and `PyMath.min()` need comparable values.
- The project uses the `pythonlike` package, so static imports can make calls look like Python: `print(...)`, `list(...)`, `len(...)`.

---

## Author

Built by Otoniel Gonzalez as an experiment to make Java feel more Pythonic.
