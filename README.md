Recurly Java Integration Library
================================

Recurly JS Client Library
-------------------------

[Recurly.js](http://js.recurly.com/) is a Javascript library which allows you to easily embed and customize PCI compliant
forms within your website. HMAC digital signatures are used to verify information sent to [Recurly](http://recurly.com/)
via your customer's browser. The signature validates that parameters you have specified in your HTML have not
been tampered with before being received by Recurly. The client library consists of helper methods to create 
and verify signatures.

Documentation: http://docs.recurly.com/recurlyjs

Installation
------------

Using the JAR:

1. Build the jar by running: mvn package
2. Add the jar to your classpath.

Using maven:

1. Install to your maven repository by running mvn install
2. Include dependency in your pom.xml:

```xml
<!-- Recurly -->
<dependency>
    <groupId>com.github.tfoxcroft</groupId>
    <artifactId>recurly-integration-library</artifactId>
    <version>2.1.0</version>
</dependency>
```

Configuration and Usage
-----------------------

For RecurlyJS usage, please see the 
[Sample Application](https://github.com/tfoxcroft/recurly_integration_sample_app) as well as  the JUnit test cases 
in [RecurlyJSTest]
(https://github.com/tfoxcroft/recurly_integration/blob/master/src/test/java/za/co/trf/recurly/RecurlyJSTest.java)

The library has been designed with Spring configuration in mind:

```xml
<!-- Recurly Integration Beans -->
<bean id="recurlyJS" class="za.co.trf.recurly.js.RecurlyJS">
    <!-- Private Key -->
    <constructor-arg value="a7e8ccc62d1d4127bcfd822a33496943" />
</bean>
```

If your Recurly private key is not directly available to your Spring configuration file (e.g. if it is stored in a
stored in a database), you can write a class implementing the KeyProvider interface and pass this through to RecurlyJS:

```xml
<!-- Recurly Integration Beans -->
<bean id="recurlyJS" class="za.co.trf.recurly.js.RecurlyJS">
    <constructor-arg>
        <!-- Private Key Provider -->
        <bean id="keyProvider" class="za.co.example.MyKeyProvider"/>
    </constructor-arg>
</bean>
```

Contributing
------------
Any contributions are welcome. Simply fork the
[Recurly Java Integration Library repository on GitHub](https://github.com/tfoxcroft/recurly_integration)
and send a Pull Request.

Acknowledgements
----------------
The library was developed for and released as open source by [Business Systems Group (Africa)](http://www.bsg.co.za).