# Contexts and Dependency Injection (CDI)

In this course, we.ll learn how to use **CDI** to enhance the behavior of your application.
> Look at

1. CDI bean, their scope and context.
2. How to use qualifiers to disambiguate between CDI beans of the same type.
3. Use interceptor to implement cross-cutting concerns.
4. Events and observers to send messages throughout our system.
5. Producer methods.
6. Disposers.

### Create a new Java Enterprise project with CDI

1. Click New Project on the Welcome screen or select File | New | Project.
2. In the wizard that opens, select Java Enterprise on the left.
3. select the Contexts and Dependency Injection (CDI) checkbox.

### What is Dependency?

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

### What is Coupling?

Classes that use other classes are said to be coupled.

- **Loosely** or **tightly**  coupled
    1. These dependencies can be either **loosely** or **tightly** couple
- Tightly coupled classes reduce code reuse
- Strive for loose coupling
- Uses interface as reference types

### What is context and Dependency Injection (CDI)?

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

```java
   SeContainer container=
        SeContainerInitializer
        .newInstance()
        .initialize();
```

- Obtain bean by name

```java
  container.select(ProductService.class)
        .get().generateCode(book);
```

- In a java EE app the container instantiates.

### What is a CDI Bean?

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
