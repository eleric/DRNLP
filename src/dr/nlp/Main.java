package dr.nlp;

import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.structure.Dictionary;
import dr.nlp.structure.Document;
import dr.nlp.token.type.TokenType;
import dr.nlp.translator.DocumentTranslator;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        DRNLPFactory factory = new DRNLPFactory();

        String inputFilename = args[0];
        String outputFilename = args[1];
        String properNounOutputFilename = args[2];

        Dictionary masterDictionary = factory.createDictionary();
        DocumentParser parser = factory.createDocumentParser(masterDictionary);
        DocumentTranslator translator = factory.createDocumentTranslator();
        Path path = Paths.get(inputFilename);
        OutputStream outputStream = new FileOutputStream(outputFilename);
        BufferedWriter bw = new BufferedWriter(new FileWriter(properNounOutputFilename));

        // Parse input file
        Document document = parser.parse(path);
        // Write to output file
        translator.write(document, outputStream);
        // Write to screen
        System.out.print(translator.translateToString(document));
        masterDictionary.getTokens(TokenType.PROPER_NOUN).stream().forEach(
                (t) -> {
                    try {
                        bw.write(t.getValue());
                        bw.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        bw.close();
        masterDictionary.getTokens(TokenType.PROPER_NOUN).stream().forEach(
                (t) -> System.out.println(t.getValue()));
    }
}
