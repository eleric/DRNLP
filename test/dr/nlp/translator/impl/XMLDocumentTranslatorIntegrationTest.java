package dr.nlp.translator.impl;

import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.structure.Document;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by eleri_000 on 10/17/2015.
 */
public class XMLDocumentTranslatorIntegrationTest {
	DRNLPFactory factory;
	DocumentParser parser;
	Path path;
	XMLDocumentTranslator xmlDocumentTranslator;

	@Before
	public void setUp() throws Exception {
		factory = new DRNLPFactory();

		parser = factory.createDocumentParser();
		URL url  = this.getClass().getClassLoader().
				getResource("resources/testDocument1.txt");
		path = Paths.get(url.toURI());
		xmlDocumentTranslator = new XMLDocumentTranslator();
	}

	@Test
	public void testTranslateToString() throws Exception {
		Document document = parser.parse(path);
		String xml = xmlDocumentTranslator.translateToString(document);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		org.w3c.dom.Document xmlDoc = builder.parse(is);
		assertThat(xmlDoc.getElementsByTagName("document").getLength(),
				greaterThan(0));
		assertThat(xmlDoc.getElementsByTagName("sentences").getLength(),
				greaterThan(0));
		assertThat(xmlDoc.getElementsByTagName("sentence").getLength(),
				greaterThan(0));
		assertThat(xmlDoc.getElementsByTagName("token").getLength(),
				greaterThan(0));
		assertThat(xmlDoc.getElementsByTagName("value").getLength(),
				greaterThan(0));
		assertThat(xmlDoc.getElementsByTagName("type").getLength(),
				greaterThan(0));
		boolean found;
		NodeList nodeList;
		Node node;
		NodeList childrenNodes;
		// assert sentences are children of document
		nodeList = xmlDoc.getElementsByTagName("document");
		node = nodeList.item(0);
		childrenNodes = node.getChildNodes();
		found = false;
		for (int i = 0; i < childrenNodes.getLength(); i++)
		{
			if (childrenNodes.item(i).getNodeName().equals("sentences"))
			{
				found = true;
			}
		}
		assertTrue(found);
		// assert sentence are children of sentences
		nodeList = xmlDoc.getElementsByTagName("sentences");
		node = nodeList.item(0);
		childrenNodes = node.getChildNodes();
		found = false;
		for (int i = 0; i < childrenNodes.getLength(); i++)
		{
			if (childrenNodes.item(i).getNodeName().equals("sentence"))
			{
				found = true;
			}
		}
		assertTrue(found);
		// assert token are children of sentence
		nodeList = xmlDoc.getElementsByTagName("sentence");
		node = nodeList.item(0);
		childrenNodes = node.getChildNodes();
		found = false;
		for (int i = 0; i < childrenNodes.getLength(); i++)
		{
			if (childrenNodes.item(i).getNodeName().equals("token"))
			{
				found = true;
			}
		}
		assertTrue(found);
		// assert value and type are children of token
		nodeList = xmlDoc.getElementsByTagName("token");
		node = nodeList.item(0);
		childrenNodes = node.getChildNodes();
		found = false;
		for (int i = 0; i < childrenNodes.getLength(); i++)
		{
			if (childrenNodes.item(i).getNodeName().equals("value"))
			{
				found = true;
			}
		}
		assertTrue(found);
		for (int i = 0; i < childrenNodes.getLength(); i++)
		{
			if (childrenNodes.item(i).getNodeName().equals("type"))
			{
				found = true;
			}
		}
		assertTrue(found);
	}
}