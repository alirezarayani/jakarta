package session01.dependencyInjection;

import javax.inject.Inject;

public class ProductService {

    @Inject
    private CodeGenerator codeGenerator;

    public Product generateCode(Product product) {
        String barcode = codeGenerator.generateCode();
        product.setCode(barcode);
        return product;
    }

}
