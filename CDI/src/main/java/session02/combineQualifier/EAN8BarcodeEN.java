package session02.combineQualifier;

@Barcode(type = Barcode.Type.EAN8)
@Language(locale = Language.Locale.ENGLISH)
public class EAN8BarcodeEN implements CodeGenerator {
    @Override
    public String generateCode() {
        return "EAN8: 818817439871";
    }
}
