package session02.advancedQualifier;

@Barcode(type = Barcode.Type.EAN5)
public class EAN5Barcode implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN5: 98188179871";
    }
}
