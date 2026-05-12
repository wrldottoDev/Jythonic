# Python-Like for Java

A small experimental library that makes Java feel more like Python.

This project recreates some of Python’s most comfortable features using pure Java:

- `print()`
- `input()`
- `len()`
- `type()`
- `range()`
- `PyList`
- slicing
- negative indexes
- slicing with steps
- `in`-style checks

The goal of this project is not to replace Java, but to explore:

- object-oriented programming
- inheritance
- generics
- method overloading
- polymorphism
- API/library design
- how Python-like behavior can be recreated in Java

---

# Features

## print()

```java
print("Hello World");
print(10);
print(5.5);
```

---

## input()

```java
String name = input("Name: ");
int age = input("Age: ");
float height = input("Height: ");
```

---

## len()

```java
print(len("Hello"));
print(len(nums));
```

Works with:

- Strings
- Arrays
- Collections
- PyList

---

## type()

```java
print(type(10));
print(type("Hello"));
print(type(true));
```

Output:

```python
<class 'int'>
<class 'str'>
<class 'bool'>
```

---

# range()

```java
for (int i : range(5)) {
    print(i);
}
```

```java
for (int i : range(2, 10, 2)) {
    print(i);
}
```

---

# PyList

```java
PyList<String> names = new PyList<>();

names.append("Oto");
names.append("Juan");

print(names);
```

Output:

```python
[Oto, Juan]
```

---

# pop()

```java
print(names.pop());
```

---

# slice()

```java
nums.slice(1, 4);
```

---

# Negative Indexes

```java
nums.pyGet(-1);
nums.pyGet(-2);
```

---

# Slicing with Step

```java
nums.slice(0, nums.len(), 2);
```

---

# in-style checks

```java
names.has("Juan");
```

---

# Example

```java
import static py.Py.*;

public class Main {

    public static void main(String[] args) {

        PyList<Integer> nums = new PyList<>();

        nums.append(10);
        nums.append(20);
        nums.append(30);
        nums.append(40);
        nums.append(50);

        print(nums);

        print(nums.slice(0, nums.len(), 2));

        print(nums.pyGet(-1));

        print(type(nums));
    }
}
```

---

# Why?

This project exists mainly for learning and experimentation.

It helps understand:

- how Python works internally
- how Java handles objects and types
- inheritance and extensions
- custom APIs and frameworks
- recreating language behavior

---

# Future Ideas

- `reverse()`
- `sort()`
- `enumerate()`
- `join()`
- `split()`
- `dict()`
- `tuple()`
- list comprehensions
- lambda helpers
- custom iterators
- Python-style string methods

---

# Author

Built by Otoniel González as an experiment to make Java feel more Pythonic.
