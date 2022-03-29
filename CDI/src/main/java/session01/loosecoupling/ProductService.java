package session01.loosecoupling;

public class ProductService {

    private CodeGenerator codeGenerator;

    ProductService(CodeGenerator codeGenerator){
        this.codeGenerator = codeGenerator;
    }

    public Product generateCode(Product product) {
        String barcode = codeGenerator.generateCode();
        product.setCode(barcode);
        return product;
    }

}
