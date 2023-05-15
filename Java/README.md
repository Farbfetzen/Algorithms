# Algorithms in Java

## Usage

```bash
mvn clean compile
mvn exec:java -Dexec.args="'heap sort'"
```

Add the argument `-v` to view a visualization.

## Import Processing dependency with Maven

The maintainers of Processing don't want to publish the jars to a public Maven repository.
You can still install the `core.jar` into your local Maven repository
by downloading Processing from <https://processing.org> and using `mvn install:install-file`.

```bash
# Example with core.jar in the current directory. Make sure to specify the correct version!
mvn install:install-file -Dfile=core.jar -DgroupId=org.processing -DartifactId=core -Dversion=<version> -Dpackaging=jar -DgeneratePom=true
```
