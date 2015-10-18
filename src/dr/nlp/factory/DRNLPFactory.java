package dr.nlp.factory;

import dr.nlp.atom.Atom;
import dr.nlp.atom.impl.PunctuationAtom;
import dr.nlp.atom.impl.WhiteSpaceAtom;
import dr.nlp.atom.impl.WordAtom;
import dr.nlp.dataInput.DataInput;
import dr.nlp.dataInput.impl.InMemoryArrayDataInput;
import dr.nlp.parser.DocumentParser;
import dr.nlp.parser.impl.DocumentParserImpl;
import dr.nlp.processor.Processor;
import dr.nlp.processor.impl.*;
import dr.nlp.structure.Dictionary;
import dr.nlp.structure.Document;
import dr.nlp.structure.Sentence;
import dr.nlp.structure.impl.DictionaryImpl;
import dr.nlp.structure.impl.DocumentImpl;
import dr.nlp.structure.impl.SentenceImpl;
import dr.nlp.translator.DocumentTranslator;
import dr.nlp.translator.impl.XMLDocumentTranslator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

/**
 * Central location to instantiate currently used implementations of the
 * core interfaces.  If an implementation needs to be swapped out it only
 * needs to be replaced here.
 * Created by eleri_000 on 10/17/2015.
 */
public class DRNLPFactory {
	public Atom createPunctuationAtom() {
		return new PunctuationAtom();
	}

	public Atom createWhiteSpaceAtom() {
		return new WhiteSpaceAtom();
	}

	public Atom createWordAtom() {
		return new WordAtom();
	}

	public DataInput createDataInput(Path path) throws IOException {
		return new InMemoryArrayDataInput(path);
	}

	public DocumentParser createDocumentParser(Dictionary masterDictionary) {
		return new DocumentParserImpl(masterDictionary);
	}

	public Processor createOrderedProcessor(
			List<Processor<Character>> processors) {
		return new OrderedProcessor(processors);
	}

	public Processor createProperNounProcessor(Dictionary masterDictionary)
			throws IOException, URISyntaxException {
		return new ProperNounProcessor(masterDictionary);
	}

	public Processor createWordProcessor(Dictionary masterDictionary) {
		return new WordProcessor(masterDictionary);
	}

	public Processor createWhiteSpaceProcessor(Dictionary masterDictionary) {
		return new WhiteSpaceProcessor(masterDictionary);
	}

	public Processor createPunctuationProcessor(Dictionary masterDictionary) {
		return new PunctuationProcessor(masterDictionary);
	}

	public Dictionary createDictionary() {
		return new DictionaryImpl();
	}

	public Document createDocument() {
		return new DocumentImpl();
	}

	public Sentence createSentence() {
		return new SentenceImpl();
	}

	public DocumentTranslator createDocumentTranslator() {
		return new XMLDocumentTranslator();
	}
}
