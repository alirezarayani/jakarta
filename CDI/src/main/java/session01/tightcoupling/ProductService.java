package session01.tightcoupling;

public class ProductService {

    private EAN13Barcode ean13Barcode;

    public ProductService(EAN13Barcode ean13Barcode) {
        this.ean13Barcode = ean13Barcode;
    }

    public Product generateCode(Product product) {
        String barcode = ean13Barcode.generateCode();
        product.setCode(barcode);
        return product;
    }
}
