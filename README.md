# connectall-junit-adapter
JUnit test listener that acts as ConnectALL Universal adapter to report test results.

ConnectALL JUnit adapter is built on top of Universal adapter configuration.

## Usecase
JUnit test results to be pushed to an end application for creating bugs from failure or to log test runs/results. 

# How to use

## ConnectALL Setup

> In order to use the JUnit adapter you will need to get the license from ConnectALL sales team. Please reach out to sales@connectall.com for licenses and quotes.

### Import specifications
* Import the zip from the `dist` directory in to ConnectALL using "Import Adapters Configuration" feature. 

### Define application links
* Create an application link in ConnectALL between JUnit and a destination application of your choice
* Navigate to `Configuration -> Connections` screen and create a new connection to JUnit, using an unique key for each of your integration group.

### Fields available
|Field|Remarks|
|---|---|
|id| a concatenated string of `Test Header` and current time in millis|
|modifiedDate| current time in `yyyy-MM-dd HH:mm:ss.SSS` format|
|testHeader| a concatenated string of the test class and test method names|
|message| failure/success message from the test method|
|description| description of the test method|
|exception| exception details if a test method is failed|
|trace| stack trace of the exception raised in the test method|

## Project Setup

### Configuring dependency

On your maven projects, add the below dependency and repository in your `pom.xml`

```xml
<dependency>
  <groupId>com.connectall.techservices</groupId>
  <artifactId>connectall-junit-adapter</artifactId>
  <version>${connectall-junit-adapter-version}</version>
</dependency>
```

```xml
<repositories>
  <repository>
    <id>connectall-public</id>
    <name>ConnectALL Public</name>
    <url>https://repo.connectall.com/repository/maven-ca-public</url>
  </repository>
</repositories>
```

`JUnitListener` overrides `RunListener#testRunFinished` method to capture the results and push them to ConnectALL. You will need to register this listener in the `RunNotifier`. 

There are two ways for your to add the listener to your JUnit tests. 
1. Extending BaseRunner classes
2. Configuring the listener in your `maven-surefire-plugin`

### Extending BaseRunner class

You can extend your BaseRunner class and register the `JUnitListener`. The example below shows how to extend `BlockJUnit4ClassRunner` and register the listener. 

```java
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class ConnectAllJUnitRunner extends BlockJUnit4ClassRunner {

	public ConnectAllJUnitRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
	}

	@Override
	public void run(final RunNotifier notifier) {
		notifier.addListener(new JUnitListener());
		super.run(notifier);
	}
}
```
Once you have your custom Runner defined you can use this in your Tests using `@RunWith` annotation.

```java
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConnectAllJUnitRunner.class) // Use the CustomRunner here
public class ConnectALLJUnitRunnerTest {
	
	@Test
	public void testSample() {
		assertTrue("This should be true",true);
	}

}
```

> ConnectALL already bundled `ConnectALLCucumberRunner` that registers the listener by extending the default `Cucumber` runner.

### Surefire plugin  configuration

If your test projects are using Maven-Surefire-Plugin to run your tests, then adding the listeners to it is even more easier. Configure the FQN (Full qualified name) of the listener in the plugin properties and you will be up and running. 

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
    <configuration>
        <properties>
            <property>
                <name>listener</name>
                <!-- Custom listener --> 
                <value>com.connectall.techservices.junit.JUnitListener</value>
            </property>
        </properties>
    </configuration>
</plugin>
```
Once your have your project Configured to use ConnectALL `JUnitListener`, you can now configure it to push the results to ConnectALL by creating a file named `ConnectALL.properties` in the classpath (typically under `src/test/resources`).

**ConnectALL.properties** file will have the below properties
* base.url -> refers to ConnectALL Base URL Endpoint for Universal Adapter ex: http://localhost:8090
* apikey -> API Key for the ConnectALL User
* applinkName -> Applicaiton Link Name configured in ConnectALL to synchronize the results

```
base.url=http://localhost:8090
apikey=aaaaaaaa-bbbb-ccccc-dddd-eeeeeeeeeeeee
applinkName=JunitJira
```

> **NOTE** Registering the listener multiple times will result in duplication of the test results, as JUnit adds listeners to a List and not as a Set; hence the same Listener has a chance to be executed multiple times. 

