package session04.simple;

public class PriceChangeEvent {
    private String stock;
    private float price;
    private float priceChange;

    public PriceChangeEvent() {
    }

    public PriceChangeEvent(String stock, float price, float priceChange) {
        this.stock = stock;
        this.price = price;
        this.priceChange = priceChange;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(float priceChange) {
        this.priceChange = priceChange;
    }
}
