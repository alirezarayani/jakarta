package session02.qualifier;

import javax.inject.Inject;

public class ProductService {

    @Inject
    @EAN13
//    @EAN5
    private CodeGenerator codeGenerator;

    public Product generateCode(Product product) {
        String barcode = codeGenerator.generateCode();
        product.setCode(barcode);
        return product;
    }

}
