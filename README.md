Recurly Java Integration Library
================================

Recurly JS Client Library

    Recurly.js is a Javascript library which allows you to easily embed and customize PCI compliant forms within
    your website. HMAC digital signatures are used to verify information sent to Recurly via your customer's browser.
    The signature validates that parameters you have specified in your HTML have not been tampered with before
    being received by Recurly. The client library consists of helper methods to create and verify signatures.

    Documentation: http://docs.recurly.com/recurlyjs


Configuration and Usage
-----------------------

For RecurlyJS usage, please refer to the JUnit test cases in RecurlyJSTest]
()


Installation
------------

Using the JAR:

1. Build the jar by running: mvn package
2. Add the jar to your classpath.

Using maven:

1. Install to your maven repository by running mvn install
2. Include dependency in your pom.xml:
<pre>
    <dependency>
        <groupId>za.co.trf.recurly</groupId>
        <artifactId>recurly-integration-library</artifactId>
        <version>1.0</version>
    </dependency>
</pre>