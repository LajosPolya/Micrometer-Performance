# Micrometer Performance

This application is used as a scaffold for performance testing Micrometer using [JMH](https://github.com/openjdk/jmh).

JMH recommends that the best way to performance test code is to package it into a JAR and import into the JMH harness.
The easiest route for this is to add your JAR to your local Maven repository from where it can be imported into the JMH
project.
To do this, use the following command:

First, build the project by running the following command in its directory.

```shell
mvn clean install
```

Then, add th JAR to your local maven repository.

```shell
 mvn install:install-file -Dfile=<path-to-jar-in-target-dir> -DgroupId=com.github.lajospolya -DartifactId=meterRegistryPerformance -Dversion=1.0-SNAPHSHOT -Dpackaging=jar
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
