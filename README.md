# JPA, JAX-RS and CDI with Shrinkwrap

This project uses JPA, CDI and JAX-RS resources deployed through a user-provided
`main()` programmatically without constructing a `.war` file during the build.

## Project `pom.xml`

This project is a normal maven project with `jar` packaging, not `war`.

    <packaging>jar</packaging>

The project adds a `<plugin>` to configure `wildfly-swarm-plugin` to
create the runnable `.jar`.

    <plugin>
      <groupId>org.wildfly.swarm</groupId>
      <artifactId>wildfly-swarm-plugin</artifactId>
      <version>${version.wildfly-swarm}</version>
      <configuration>
        <mainClass>org.wildfly.swarm.examples.jpa.Main</mainClass>
      </configuration>
      <executions>
        <execution>
          <goals>
            <goal>package</goal>
          </goals>
        </execution>
      </executions>
    </plugin>

## Project `main()`

Since this project deploys CDI and JAX-RS resources without a `.war` being constructed, it
provides its own `main()` method (specified above via the `wildfly-swarm-plugin`) to
configure the container and deploy the resources programmatically. Additionally,
it deploys the JDBC driver jar using a simplified Maven GAV (no version is required)
and deploys a datasource.

This method constructs a new default Container, which automatically
initializes all fractions (or subsystems) that are available.

## Run

You can run it many ways:

* mvn package && java -jar ./target/example-jpa-jaxrs-cdi-shrinkwrap-swarm.jar
* mvn wildfly-swarm:run
* In your IDE run the `org.wildfly.swarm.examples.jpa.Main` class

## Use

    http://localhost:8080/messages