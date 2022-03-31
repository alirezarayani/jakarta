package session02.combineQualifier;

@Barcode(type = Barcode.Type.EAN8)
@Language(locale = Language.Locale.SPANISH)
public class EAN8BarcodeES implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN8: 818817439871";
    }
}
