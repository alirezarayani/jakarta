package session04.simple;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.concurrent.TimeUnit;

public class BootStrap {
    public static void main(String[] args) throws InterruptedException {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        System.out.println("------------------------------------------");
        container.select(StockObserver.class).get()
                .priceChange(new PriceChangeEvent("Bid Burger Crop", 100f, 8f));

        System.out.println("------------------------------------------");
        container.select(StockObserver.class).get()
                .priceChange(new PriceChangeEvent("Banko Crop", 150f, -7f));
        System.out.println("------------------------------------------");
        container.select(StockObserver.class).get()
                .priceChange(new PriceChangeEvent("Food Crop", 250f, -100f));
        System.out.println("------------------------------------------");

    }
}
