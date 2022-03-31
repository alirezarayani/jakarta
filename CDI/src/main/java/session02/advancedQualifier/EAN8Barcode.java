package session02.advancedQualifier;

@Barcode(type = Barcode.Type.EAN8)
public class EAN8Barcode implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN8: 818817439871";
    }
}
