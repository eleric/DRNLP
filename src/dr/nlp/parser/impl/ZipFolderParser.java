package dr.nlp.parser.impl;

import com.sun.nio.zipfs.ZipFileSystemProvider;
import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.parser.FolderParser;
import dr.nlp.structure.Dictionary;
import dr.nlp.structure.Document;
import dr.nlp.structure.Folder;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eleri_000 on 10/18/2015.
 */
public class ZipFolderParser implements FolderParser {
	DRNLPFactory factory;
	Dictionary masterDictionary;
	DocumentParser documentParser;

	public ZipFolderParser(Dictionary masterDictionary) {
		this.masterDictionary = masterDictionary;
		factory = new DRNLPFactory();
		documentParser = factory.createDocumentParser(masterDictionary);
	}

	@Override
	public Folder parse(Path path) throws IOException {
		final Folder folder = factory.createFolder();
		ZipFileSystemProvider zfsp = new ZipFileSystemProvider();
		Map<String, String> zip_properties = new HashMap<>();
		zip_properties.put("create", "false");
		zip_properties.put("encoding", "UTF-8");

		try (FileSystem zipfs = zfsp.newFileSystem(path, zip_properties)) {
			Path root = zipfs.getPath("/");
			Files.list(root).forEach((p)->{
				if (Files.isDirectory(p))
				{
					try {
						Files.list(p).filter((f)->!f.toString().startsWith("/_")).forEach(
								(p2) -> {
									try {
										folder.getDocuments().add(documentParser.parse(p2));
									} catch (IOException e) {
										throw new RuntimeException(e);
									}
								});
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			});
		}
		return folder;
	}
}
