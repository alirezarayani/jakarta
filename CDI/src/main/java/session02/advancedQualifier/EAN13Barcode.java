package session02.advancedQualifier;

@Barcode(type = Barcode.Type.EAN13)
public class EAN13Barcode implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN13: 1335443243543";
    }
}
