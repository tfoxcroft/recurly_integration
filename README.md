Recurly Java Integration Library
================================

Recurly is a software as a service company that provides recurring billing management as an outsourced service. The
Recurly Java Integration Library is an open source library aimed at simplifying the integration of a java application
with Recurly through RecurlyJS and Recurly's REST API.

Project home page: http://tfoxcroft.github.com/recurly_integration/

Recurly JS Client
-----------------

[Recurly.js](http://js.recurly.com/) is a Javascript library which allows you to easily embed and customize PCI compliant
forms within your website. HMAC digital signatures are used to verify information sent to [Recurly](http://recurly.com/)
via your customer's browser. The signature validates that parameters you have specified in your HTML have not
been tampered with before being received by Recurly. The client library contains helper methods to create these signatures.

Documentation: http://docs.recurly.com/recurlyjs

Recurly REST API Client
-----------------------

The client library contains services to interact with Recurly's REST API (API v2)

Documentation: http://docs.recurly.com/api

Installation
------------

######Using the JAR:######

1. Build the jar by running: mvn package
2. Add the jar to your classpath.

######Using maven:######

The Recurly Integration Library is available from the Maven Central Repository, so you do not need to add any additional
repositories to your POM. Simply add the necessary dependency:

```xml
<!-- Recurly -->
<dependency>
    <groupId>com.github.tfoxcroft</groupId>
    <artifactId>recurly-integration-library</artifactId>
    <version>2.1.1</version>
</dependency>
```

To install snapshot versions to your local maven repository, checkout the project from GitHub and run the following
from the project root:

```console
mvn install
```

Configuration and Usage
-----------------------

For RecurlyJS usage, please see the 
[Sample Application](https://github.com/tfoxcroft/recurly_integration_sample_app) as well as  the JUnit test cases.

The library has been designed with Spring configuration in mind. Configure the beans for the RecurlyJS client as follows:

```xml
<!-- Recurly Integration Beans -->
<bean id="recurlyJS" class="za.co.trf.recurly.js.RecurlyJS">
    <!-- Private Key -->
    <constructor-arg value="a7e8ccc62d1d4127bcfd822a33496943"/>
</bean>
```

Similarly, configure beans for the Recurly REST API client as follows:

```xml
<!-- Recurly Integration Beans -->
<bean id="recurlyAccountWebService" class="za.co.trf.recurly.api.rest.RecurlyAccountWebServiceImpl">
    <property name="recurlyRestTemplate" ref="recurlyRestTemplate"/>
</bean>

<bean id="recurlySubscriptionWebService" class="za.co.trf.recurly.api.rest.RecurlySubscriptionWebServiceImpl">
    <property name="recurlyRestTemplate" ref="recurlyRestTemplate"/>
</bean>

<bean id="recurlyJSResultService" class="za.co.trf.recurly.api.rest.RecurlyJSResultServiceImpl">
    <property name="recurlyRestTemplate" ref="recurlyRestTemplate"/>
</bean>

<bean id="recurlyRestTemplate" class="za.co.trf.recurly.api.rest.RecurlyRestTemplate">
    <constructor-arg ref="recurlyAPIKeyProvider"/>
</bean>

<bean id="apiKeyProvider" class="za.co.example.MyAPIKeyProvider"/>

<bean id="privateKeyProvider" class="za.co.example.MyPrivateKeyProvider"/>
```

Note that in cases where your Recurly private or API key is not directly available to your Spring configuration file
(e.g. if it is stored in a stored in a database), you are able to write a class implementing the KeyProvider interface
and pass this through to RecurlyJS and RecurlyRestTemplate respectively.

Contributing
------------
Any contributions are welcome. Simply fork the [Recurly Integration Library](https://github.com/tfoxcroft/recurly_integration)
repository on GitHub and open a Pull Request.

Acknowledgements
----------------
The library was developed for and released as open source by [Business Systems Group (Africa)](http://www.bsg.co.za).