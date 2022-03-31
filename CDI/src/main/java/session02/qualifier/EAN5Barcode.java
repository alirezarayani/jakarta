package session02.qualifier;

@EAN5
public class EAN5Barcode implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN5: 98188179871";
    }
}
