# Contexts and Dependency Injection (CDI)

In this course, we.ll learn how to use **CDI** to enhance the behavior of your application.
> Headlines

1. CDI bean, their scope and context.
2. How to use qualifiers to disambiguate between CDI beans of the same type.
3. Use interceptor to implement cross-cutting concerns.
4. Events and observers to send messages throughout the system.
5. Producer methods.
6. Disposers.
---
> Create a new Java Enterprise project with CDI

1. Click New Project on the Welcome screen or select File | New | Project.
2. In the wizard that opens, select Java Enterprise on the left.
3. select the Contexts and Dependency Injection (CDI) checkbox.
---
> What is Dependency?

- Requirement for other classes
    1. Dependency is about the requirement that a class has to use other classes for it to function properly


- Single-purpose classes
    1. That specialize in doing just one thing.
    2. One class will very often use a functionality of another.


- Class X depends on/uses class Y
    1. X is said to be dependent on class Y
    2. Y is a dependency of class X


- Allows reuse of functionality
    1. Without duplicating code

> What is Coupling?

Classes that use other classes are said to be coupled.

- **Loosely** or **tightly**  coupled
    1. These dependencies can be either **loosely** or **tightly** couple
- Tightly coupled classes reduce code reuse
- Strive for loose coupling
- Uses interface as reference types

> What is context and Dependency Injection (CDI)?

- Two services working together
- Context service and injection service
    1. The context service manages the object, and injection service resolves dependencies and inject the object.

Use `@Inject` annotation

```java
public class ProductService {

    @Inject
    private CodeGenerator codeGenerator;

    public Product generateCode(Product product) {
        String barcode = codeGenerator.generateCode();
        product.setCode(barcode);
        return product;
    }
}
```

```java
public class BootStrap {

    public static void main(String... args) {
        SeContainer container =
                SeContainerInitializer
                        .newInstance()
                        .initialize();

        Product book = new Product("Book");

        container.select(ProductService.class)
                .get().generateCode(book);
        System.out.println(book.getCode());

        container.close();
    }
}
```

- Enumeration of all managed instances

What is the context service provided by the CDI container?

You can think of the CDI container as an enumeration of all the instances that the CDI container manages,

Object can be added and removed from context and are made available for dependency injection.

- Lifecycle managed by the container.

> Scope type

1. `@RequestScoped`
2. `@ApplicationScoped`
3. `@SessionScoped`
4. `@ConversationScoped`
5. `@Dependent`

> Maven dependencies

```xml

<dependency>
    <groupId>javax</groupId>
    <artifactId>javaee-api</artifactId>
    <version>8.0</version>
</dependency>
```

**java se**

```xml

<dependency>
    <groupId>org.jboss.weld.se</groupId>
    <artifactId>weld-se-core</artifactId>
    <version>5.0.0.CR2</version>
</dependency>
```

### Two CDI implementation

1. JBoss Weld(the Reference implementation)
2. Apache OpenWebBeans

- CDI extensions: Apache DeltaSpike and JBoss Seam


- Switch on bean discovery

The Bean discovery process occurs during application deployment.

The container finds classes that confirm to the requirements of a CDI bean and instantiates them.

The discovery of beans during start-up.

Deployment cancelled if error occurs.

Need a  `resources/META-INF/bean.xml` file.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/beans_2_0.xsd"
       bean-discovery-mode="all">
</beans>
```

- CDI 2.0 user in java SE

Using CDI in a java SE application, you just need to instantiate the CDI container

```java
   SeContainer container=
        SeContainerInitializer
        .newInstance()
        .initialize();
```

- Obtain bean by name

can Access an instance of bean be selecting it by name from the container.
```java
  container.select(ProductService.class)
        .get().generateCode(book);
```

- In a java EE app the container instantiates.
---
> What is a CDI Bean?

- CDI bean is a POJO ( plain old java object )

A CDI Bean is a POJO that has been automatically instantiated by the CDI container and is injected into all and any
qualifying injection points in the application.

- Bean discovery initiated during deployment

The CDI container initiates the Bean discovery process during deployment.

This Process scans the class path POJOs.

1. It is not an inner class.
2. It is a non-abstract class, or an annotated Decorator.
3. It does not implement a service provider interface extension.
4. It is not annotated Vetoed or in a package annotated Vetoed.

#### It has an appropriate construction, which can be either:

- A constructor with no parameters or
- It declares a constructor annotated inject

### CDI Producers:

How to make an unmanaged bean compliant classes, managed and therefor injectable? CDI producers.

A producer method is a method annotated producers that return an object that is constructs.

### How to use a CDI Bean?

- Injecting into a qualifying bean.

Inject annotation can be placed on :

1. a field.
2. a method.
3. a constructor.

- Use `@Vetoed` to prevent instantiation

- Use `@Alternative` to provide alternative implementation.

### Bean lifecycle management.

- CDI beans have a lifecycle
    1. it's lifecycle managed by the CDI container
    2. CDI bean created by the container during the application start up.

- Callbacks: `@PostConstruct` and `@PreDestroy`

---

### What Are Qualifiers?

Qualifiers are custom annotations that mark the injection points and the concrete implementations so that matching can
occur when more than one bean of the same type exists.

By default, all managed beans are annotated with a default qualifier simply called default (`@Default`).Although you
won't see it when you inspect a bean.

All the injection points are annotated with the same qualifier as well.

```java

@Default
public class EAN5Barcode implements CodeGenerator {
}
```

```java

@Default
public class EAN8Barcode implements CodeGenerator {
}
```

We cannot rely on the default qualifier to help us, It does nothing to disambiguate our bean.

So custom qualifiers are needed.

**Created a Custom qualifier**

```java

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface EAN13 {
}
```

Once we have a qualifier, we use it to annotate the concrete implementation.

```java

@EAN13
public class EAN13Barcode implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN13: 1335443243543";
    }
}
```

The injection point where we want this implementation to be injected should also be annotated with the qualifier.

```java
public class ProductService {

    //Field injection point
    @Inject
    @EAN13
    private CodeGenerator codeGenerator;

    // constructor injection point
    @Inject
    public ProductService(@EAN13 CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    //setter injection point
    @Inject
    public void setCodeGenerator(@EAN13 CodeGenerator codeGenerator) {
    }
}
```

### Advanced qualifiers

We can have as many qualifiers as there are implementations. However, in a large system, this may be a very large number
and result in a lot of qualifier classes that do little other than mark injection points and implementations.

```java

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Barcode {
    Type type() default Type.EAN13;

    enum Type {
        EAN5, EAN8, EAN13;
    }
}
```

---

## What are producer methods?

Producer methods overcome inherent issues with letting the container manage the instantiation of beans.

You can allow any class to be injectable.

This includes classes that we have created programmatically, classes that require some custom initialization, types
whose implementation might change at runtime, and interestingly, those classes that we don't have control over such as
java core classes, and classes from compiled third party APIs.

```java

@Produces
public ArrayList<Integer> get(){
        return new ArrayList<Integer>(){{
        add(new Random().nextInt(100));
        add(new Random().nextInt(100));
        add(new Random().nextInt(100));
        }};
        }
```

By default, a producer method creates an instance of a bean that adopts the same scope as the client that uses it.

What this means is if the produced instance is injected into a bean that is, say, session scoped, then the injected
instance will be session scoped.

```java
@Produces
@ApplicationScoped
public ArrayList<Integer> get(){
        return new ArrayList<Integer>(){{
        add(new Random().nextInt(100));
        add(new Random().nextInt(100));
        add(new Random().nextInt(100));
        }};
        }
```

## Disposes for producers

It'll remove a produced bean when it's work is completed.

The disposer method is always matched to a producer method .

To crete a disposer method, you annotate the parameter that is the same type s the instance you want to destroy

```java
import javax.enterprise.context.SessionScoped;

@Produces
@SessionScoped
public ArrayList<Integer> get(){
        return new ArrayList<Integer>(){{
        add(new Random().nextInt(100));
        add(new Random().nextInt(100));
        add(new Random().nextInt(100));
        }};
        }
```

```java
public void clearArray(@isposes ArrayList<Integer> numbers){
        number.clear();
        }
```

---

## What are events and observers?

The CDI API provides a lightweight event and observer feature that implements the observer pattern in a very decoupled
way.

The idea behind the observer pattern is that an object that changes its state can inform other objects that the change
has occurred.

It's about passing messages from one object to another.

The purpose is to communicate state change that provokes some action in another part of the system.

example:

- PriceChange is an event.
- Event fired by the observer.
- TradingBots are subscribers.

The observers and subscribers are completely decouple from each other, which means that the observer has no knowledge of
the existence of the subscribers.

There may be hundreds of subscribers or none at all.

The programmer can add and remove subscribers without the observers being effected.

### Three parts:

1. Event class

```java
public class PriceChangeEvent {
    private String stock;
    private float price;
    private float priceChange;
}
```

2. Fire the event

```java
public class StockObserver {

    @Inject
    private Event<PriceChangeEvent> event;

    public void priceChange(PriceChangeEvent priceChangeEvent) {
        event.fire(priceChangeEvent);
    }
}

```

3. Subscribers

```java
public class BuyTradingBot {

    public void action(@Observes PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() > 0) {
            System.out.println("BUY: " + priceChangeEvent.getStock());
        }
    }
}


```

```java
public class SellTradingBot {

    public void action(@Observes PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() < 0) {
            System.out.println("SELL: " + priceChangeEvent.getStock());
        }
    }
}

```

**There are a few drawbacks to this feature.**

- one is that if an exception is thrown in one of the subscribers then unexecuted subscribers will not receive any
  notification.

Now this issue is resolved by either catching all executions in all observers or by using asynchronous events.

```java
public class SellTradingBot {

    public void action(@Observes PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() < 0) {
            System.out.println("SELL: " + priceChangeEvent.getStock());
        }
        throw new NullPointerException();
    }
}

```

- The second issue is that if you have more than one subscriber listening for the same event there is no way to control
  the order in which the subscribers are executed.

This issue is fixed by adding a priority annotation to the subscriber.It identifies the order in which those subscribers
should be executed.

Now this feature is only available with asynchronous subscribers.

Asynchronous events are fired in separate threads, so each event gets its own thread and because of this if an exception
is found in subscriber, no other subscriber is affected.

1. Change `fire()` method to `fireAsync()`

```java
public class StockObserver {

    @Inject
    private Event<PriceChangeEvent> event;

    public void priceChange(PriceChangeEvent priceChangeEvent) {
        event.fireAsync(priceChangeEvent);
    }
}
```

2. Change `@Observer` annotation to `@ObservesAsync`in subscribers.

```java
public class BuyTradingBot {

    public void action(@ObservesAsync PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() > 0) {
            System.out.println("BUY: " + priceChangeEvent.getStock());
        }
    }
}

```

- We can also add priority order (`@Priority(100)`) to subscribers that respond to the same event.

Lower priorities are executed first.

```java
public class SellTradingBot {

    public void action(@ObservesAsync @Priority(100) PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() < 0) {
            System.out.println("SELL: " + priceChangeEvent.getStock());
        }
    }
}
```

> What are interceptor?

Interceptor are about adding behaviour to existing code to solve cross-cutting concerns and are part of the
aspect-oriented programming paradigm.

Cross-cutting concerns are non-business-related concerns, such as logging and security.

They don't solve a business problem, but nevertheless are an important part of an application's make up.

The idea is that such concerns should not be addressed at the same time, or in the same place as business concerns.

And should therefore be separate from the application's business logic.

Interceptors rely on code injection to add the desired behavior or functionality to each point of an existing code base
that is identified as a qualifying injection point.

It does this by intercepting method calls and executing desired logic before and after it executes the method.

You have one point of reference for your entire cross-cutting code.Any changes you want to make to this logic is done in
just one place, and applies to all methods.

The cross-cutting logic that you want to execute before and after the method is encapsulated in a class annotated
interceptor.

```java

@Interceptor
@Transactional
public class LoggerInterceptor {

    @AroundInvoke
    private Object doMethodLogging(InvocationContext ic) throws Exception {
        // Before method call logic
        Object returnValue = ic.proceed();
        // After method returns logic
        return returnValue;
    }
}
```

Sample code:

```java

@Interceptor
@Logged
@Priority(Interceptor.Priority.APPLICATION + 100)
public class LoggerInterceptor {

    @AroundInvoke
    private Object doMethodLogging(InvocationContext ic) throws Exception {
        System.out.println("Method name: " + ic.getMethod().getName());
        System.out.println("Parameters : ");
        Arrays.stream(ic.getParameters()).map(Object::toString).forEach(System.out::println);
        return ic.proceed();
    }
}

```

```java

@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Logged {
} 
```

```java

@Logged
public class CustomerService {

    public CustomerService() {
    }

    public void suspendCustomerAccount(String customerName, String reason) {
        // perform logic that suspends the customer's account.
    }

    public void internalAccountAudit(String customerName) {
        // perform internal audit logic
    }
}
```

### Intercept constructors

Use ` @AroundConstruct`

```java

@Interceptor
@Logged
@Priority(Interceptor.Priority.APPLICATION + 100)
public class LoggerInterceptor {

    @AroundConstruct
    private Object doClassLogging(InvocationContext ic) throws Exception {
        Long start = System.currentTimeMillis();
        Object returnValue = ic.proceed();
        Long end = System.currentTimeMillis();
        System.out.println("Construction time: " + (end - start));
        return returnValue;
    }
}
```

```java

@Logged
public class CustomerService {

    public CustomerService() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
    }
}
```

> What are alternatives?

The alternative feature allows the developer to provide an alternative implementation of a bean and to use it for
different purposes.

### Common use:

1. Handling client-specific business logic
2. To specify beans that are valid for a particular deployment scenario.
3. To create test versions of beans to be used for testing.

To make an alternative bean available for use, it must be annotated alternative `@Alternative` and be defined in the
beans.xml file with the alternative element.

```xml

<beans xmlns="http://java.sun.com/xml/ns/javaee">
    <alternatives>
        <class>
            session05.WebServiceTest
        </class>
    </alternatives>
</beans>
```

. Must implement the same interface
`@ Inject WebService webService`

Now when the application is deployed, the alternative is used instead.

```java
public class BootStrap {
    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance()
                .disableDiscovery()
                .addPackages(WebService.class)
                .selectAlternatives(CustomerWebServiceTest.class)
                .initialize();

        container.select(UseWebService.class).get().go();
    }
}
```

> What is the vetoed annotation?

In some cases, you may not want a bean to be instantiated.

You can achieve this by adding the vetoed `@Vetoed` annotation to the class definition of the bean.

```java
import javax.enterprise.inject.Vetoed;

@Vetoed
public class CustomerService {
}
```

You can also exclude an entire package by annotating the package in the same way.

```java
@Vetoed
package ir.lazydeveloper;

import javax.enterprise.inject.Vetoed;
```

---
> What are bean scopes and context?

Beans are managed by the CDI container (creation, injection, and destruction)

| Scope        | Annotation          | Maps to...        |
|:-------------|---------------------|:------------------|
| Request      | @RequestScoped      | HTTP request      |
| Session      | @SessionScoped      | Http Session      |
| Application  | @ApplicationScoped  | A web application |
| Conversation | @ConversationScoped | JSF lifecycle     |
| Dependent    | @Dependent          | Client's scope    |
| Singleton    | @Singleton          | Application       |
