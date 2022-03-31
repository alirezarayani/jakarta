package session02.combineQualifier;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class BootStrap {

    public static void main(String... args) {

        SeContainer container =
                SeContainerInitializer
                        .newInstance()
                        .initialize();

        System.out.println("------------------------------------------------");

        Product book = new Product("Book");
        container.select(ProductService.class)
                .get().generateCode(book);
        System.out.println(book.getCode());

        System.out.println("------------------------------------------------");

        container.close();
    }
}
