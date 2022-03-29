package session01.dependencyInjection;

public class EAN13Barcode implements CodeGenerator {
    public String generateCode() {
        return "EAN13: 9780735200449";
    }
}
