package dr.nlp;

import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.structure.Document;
import dr.nlp.translator.DocumentTranslator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        DRNLPFactory factory = new DRNLPFactory();

        DocumentParser parser = factory.createDocumentParser();
        DocumentTranslator translator = factory.createDocumentTranslator();
        Path path = Paths.get("C:\\DigitalReasoning\\unzipped\\nlp_data.txt");

        Document document = parser.parse(path);
        String translation = translator.translateToString(document);
        System.out.print(translation);
    }
}
