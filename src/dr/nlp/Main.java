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

//        ZipFileSystemProvider zfsp = new ZipFileSystemProvider();
//        Map<String, String> zip_properties = new HashMap<>();
//        zip_properties.put("create", "false");
//        zip_properties.put("encoding", "UTF-8");
//        //FileSystem zipfs;
//        Path nlp_data2 = Paths.get("nlp_data");
//        try (FileSystem zipfs = zfsp.newFileSystem(path, zip_properties)) {
//            Path nlp_data = zipfs.getPath("nlp_data");
//            Path root = zipfs.getPath("/");
//            Files.list(root).forEach((p)->{
//                if (Files.isDirectory(p))
//                {
//                    System.out.println("Dir: " + p.toString());
//                    try {
//                        Files.list(p).filter((f)->!f.toString().startsWith("/_")).forEach((p2)->System.out.println(p2.toString()));
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//        }

//        zipfs.getRootDirectories().forEach((p)->System.out.println(p.toString()));
//        zipfs.getFileStores().forEach((p) -> System.out.println(p.name()));
//        zipfs. ().forEach((p) -> System.out.println(p.name()));
    }

//    private static FileSystem createZipFileSystem(String zipFilename,
//            boolean create)
//            throws IOException {
//        // convert the filename to a URI
//        final Path path = Paths.get(zipFilename);
//        final URI uri = URI.create("jar:file:" + path.toUri().getPath());
//
//        final Map<String, String> env = new HashMap<>();
//        if (create) {
//            env.put("create", "true");
//        }
//        return FileSystems.newFileSystem(uri, env);
//    }
//
//    public static void unzip(String zipFilename, String destDirname)
//            throws IOException{
//
//        final Path destDir = Paths.get(destDirname);
//        //if the destination doesn't exist, create it
//        if(Files.notExists(destDir)){
//            System.out.println(destDir + " does not exist. Creating...");
//            Files.createDirectories(destDir);
//        }
//
//        try (FileSystem zipFileSystem = createZipFileSystem(zipFilename, false)){
//            final Path root = zipFileSystem.getPath("/");
//
//            //walk the zip file tree and copy files to the destination
//            Files.walkFileTree(root, new SimpleFileVisitor<Path>(){
//                @Override
//                public FileVisitResult visitFile(Path file,
//                        BasicFileAttributes attrs) throws IOException {
//                    final Path destFile = Paths.get(destDir.toString(),
//                            file.toString());
//                    System.out.printf("Extracting file %s to %s\n", file, destFile);
//                    Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
//                    return FileVisitResult.CONTINUE;
//                }
//
//                @Override
//                public FileVisitResult preVisitDirectory(Path dir,
//                        BasicFileAttributes attrs) throws IOException {
//                    final Path dirToCreate = Paths.get(destDir.toString(),
//                            dir.toString());
//                    if(Files.notExists(dirToCreate)){
//                        System.out.printf("Creating directory %s\n", dirToCreate);
//                        Files.createDirectory(dirToCreate);
//                    }
//                    return FileVisitResult.CONTINUE;
//                }
//            });
//        }
//    }
}
