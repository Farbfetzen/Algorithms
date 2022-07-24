# Algorithms in Java

## Usage

```text
mvn clean compile exec:java -Dexec.args="'heap sort'"
```

## Import Processing dependency with Maven

The maintainers of Processing don't want to publish the jars to a public Maven repository.
You can still install the `core.jar` into your local Maven repository
by downloading Processing from <https://processing.org> and using `mvn install:install-file`.

```text
# Example with core.jar version 4.0b8 in the current directory:
mvn install:install-file -Dfile=core.jar -DgroupId=org.processing -DartifactId=core -Dversion=4.0b8 -Dpackaging=jar -DgeneratePom=true
```
