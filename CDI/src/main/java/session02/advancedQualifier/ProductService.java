package session02.advancedQualifier;

import javax.inject.Inject;

public class ProductService {

    @Inject
//    @Barcode
    @Barcode(type = Barcode.Type.EAN8)
    private CodeGenerator codeGenerator;

    public Product generateCode(Product product) {
        String barcode = codeGenerator.generateCode();
        product.setCode(barcode);
        return product;
    }

}
