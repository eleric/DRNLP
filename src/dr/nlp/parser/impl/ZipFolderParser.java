package dr.nlp.parser.impl;

import com.sun.nio.zipfs.ZipFileSystemProvider;
import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.parser.FolderParser;
import dr.nlp.structure.Dictionary;
import dr.nlp.structure.Document;
import dr.nlp.structure.Folder;

import java.io.IOException;
import java.nio.file.*;
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
			Files.walk(root, FileVisitOption.FOLLOW_LINKS)
					.filter(this::ignorePath).filter((p)->!Files.isDirectory(p))
					.forEach(
							(p2) -> {
								try {
									folder.getDocuments().add(documentParser.parse(p2));
								} catch (IOException e) {
									throw new RuntimeException(e);
								}
							});
		}
		return folder;
	}

	// Data file I was given to test with has _MACOSX folder that
	// needs to be ignore
	private boolean ignorePath(Path path)
	{
		return !path.toString().startsWith("/_");
	}
}
