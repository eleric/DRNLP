package dr.nlp;

import com.sun.nio.zipfs.ZipFileSystemProvider;
import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.parser.FolderParser;
import dr.nlp.parser.impl.ZipFolderParser;
import dr.nlp.structure.Dictionary;
import dr.nlp.structure.Document;
import dr.nlp.structure.Folder;
import dr.nlp.token.type.TokenType;
import dr.nlp.translator.DocumentTranslator;
import dr.nlp.translator.FolderTranslator;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args)
            throws IOException, URISyntaxException {
        DRNLPFactory factory = new DRNLPFactory();

        String inputFilename = args[0];
        String outputFilename = args[1];
        String properNounOutputFilename = args[2];

        Dictionary masterDictionary = factory.createDictionary();
        FolderParser parser = factory.createZipFolderParser(masterDictionary);
//        DocumentParser parser = factory.createDocumentParser(masterDictionary);
        FolderTranslator translator = factory.createFolderTranslator();
        Path path = Paths.get(inputFilename);
        OutputStream outputStream = new FileOutputStream(outputFilename);
        BufferedWriter bw = new BufferedWriter(new FileWriter(properNounOutputFilename));

        // Parse input file
        Folder folder = parser.parse(path);
        // Write to output file
        translator.write(folder, outputStream);
        // Write to screen
        System.out.print(translator.translateToString(folder));
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
