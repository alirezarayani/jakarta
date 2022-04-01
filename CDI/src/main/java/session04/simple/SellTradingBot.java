package session04.simple;

import javax.enterprise.event.Observes;

public class SellTradingBot {

    public void action(@Observes PriceChangeEvent priceChangeEvent) {
        if (priceChangeEvent.getPriceChange() < 0) {
            System.out.println("SELL: " + priceChangeEvent.getStock());
        }
    }
}
