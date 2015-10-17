package dr.nlp.parser.impl;

import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.structure.Document;
import dr.nlp.token.type.TokenType;
import dr.nlp.translator.DocumentTranslator;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by eleri_000 on 10/17/2015.
 */
public class DocumentParserImplIntegrationTest {
	DRNLPFactory factory;
	DocumentParser parser;
	Path path;

	@Before
	public void setUp() throws Exception {
		factory = new DRNLPFactory();

		parser = factory.createDocumentParser();
		URL url  = this.getClass().getClassLoader().
				getResource("resources/testDocument1.txt");
		path = Paths.get(url.toURI());
	}

	@Test
	public void testParse() throws Exception {
		Document document = parser.parse(path);
		assertEquals("Number of Sentences not correct.", 3,
				document.getSentences().size());
		assertEquals("Number of Tokens in first Sentence not correct.", 11,
				document.getSentences().get(0).getTokens().size());
		assertEquals("Number of Punctuation Tokens in first Sentence not correct.", 3,
				document.getSentences().get(0).getTokens().stream()
						.filter((t) -> t.getType()
								== TokenType.PUNCTUATION).count());
		assertEquals("Number of Word Tokens in first Sentence not correct.", 4,
				document.getSentences().get(0).getTokens().stream()
						.filter((t) -> t.getType() == TokenType.WORD).count());
		assertEquals("Number of White Spaces Tokens in first Sentence not correct.", 4,
				document.getSentences().get(0).getTokens().stream()
						.filter((t) -> t.getType() == TokenType.WHITE_SPACE).count());
	}
}