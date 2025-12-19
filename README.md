# Micrometer Performance

## Performance Testing with JMH

This application is used as a scaffold for performance testing Micrometer using [JMH](https://github.com/openjdk/jmh).

JMH recommends that the best way to performance test code is to package it into a JAR and import into the JMH harness.
The easiest route for this is to add your JAR to your local Maven repository from where it can be imported into the JMH
project.
To do this, use the following command:

First, build the project by running the following command in the project's directory.

```shell
mvn clean install
```

Then, add th JAR to your local maven repository.

```shell
 mvn install:install-file -Dfile=<path-to>/target/meterRegistryPerformance-1.0-SNAPSHOT.jar -DgroupId=com.github.lajospolya -DartifactId=meterRegistryPerformance -Dversion=1.0-SNAPHSHOT -Dpackaging=jar
```

Follow https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html for other examples.

Then, import the JAR as any other dependency using the Maven project's POM.

```xml
<dependency>
    <groupId>com.github.lajospolya</groupId>
    <artifactId>meterRegistryPerformance</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Performance Testing with JFR

This project contains multiple `main` classes. Each main class repeatedly increments a counter for one minute straight.
The purpose of this is to repeatedly run the same operation over and over again to get an idea of how much memory the 
application uses when measured with JFR.

To begin, choose which `main` class you want to package into the JAR. Update the [pom.xml](pom.xml) to contain the class
you want to test. For example,

```xml
<mainClass>com.github.lajospolya.MainCacheHashMapTagless</mainClass>
```

Then build the project,

```shell
mvn clean install
```

Once the project is build it can be run with JFR using the following command,

```shell
 java -XX:StartFlightRecording:filename=<filename.jfr> -jar target/meterRegistryPerformance-1.0-SNAPSHOT-jar-with-dependencies.jar
```
where `<filename.jfr` represents the JFR file's name.

Once the file is generated, it can be opened using JDK Mission Control.
