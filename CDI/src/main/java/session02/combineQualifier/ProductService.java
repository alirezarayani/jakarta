package session02.combineQualifier;

import javax.inject.Inject;

public class ProductService {

    @Inject
//    @Barcode
    @Barcode(type = Barcode.Type.EAN8)
    @Language(locale = Language.Locale.ENGLISH)
    private CodeGenerator codeGenerator;

    public Product generateCode(Product product) {
        String barcode = codeGenerator.generateCode();
        product.setCode(barcode);
        return product;
    }

}
