package dr.nlp;

import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.structure.Document;
import dr.nlp.translator.DocumentTranslator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        DRNLPFactory factory = new DRNLPFactory();

        String inputFilename = args[0];
        String outputFilename = args[1];

        DocumentParser parser = factory.createDocumentParser();
        DocumentTranslator translator = factory.createDocumentTranslator();
        Path path = Paths.get(inputFilename);
        OutputStream outputStream = new FileOutputStream(outputFilename);

        // Parse input file
        Document document = parser.parse(path);
        // Write to output file
        translator.write(document, outputStream);
        // Write to screen
        System.out.print(translator.translateToString(document));
    }
}
