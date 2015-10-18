package dr.nlp.parser.impl;

import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.parser.FolderParser;
import dr.nlp.structure.Dictionary;
import dr.nlp.structure.Document;
import dr.nlp.structure.Folder;
import dr.nlp.token.type.TokenType;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by eleri_000 on 10/18/2015.
 */
public class ZipFolderParserTest {
	DRNLPFactory factory;
	FolderParser parser;
	Path path;
	@Before
	public void setUp() throws Exception {
		factory = new DRNLPFactory();

		Dictionary masterDictionary = factory.createDictionary();

		parser = factory.createZipFolderParser(masterDictionary);
		URL url  = this.getClass().getClassLoader().
				getResource("resources/testZipped.zip");
		path = Paths.get(url.toURI());
	}

	@Test
	public void testParse() throws Exception {
		Folder folder = parser.parse(path);
		assertEquals("Number of Documents not correct.", 2,
				folder.getDocuments().size());
	}
}