package dr.nlp.parser.impl;

import dr.nlp.dataInput.DataInput;
import dr.nlp.dataInput.impl.InMemoryArrayDataInput;
import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.processor.Processor;
import dr.nlp.processor.impl.WordProcessor;
import dr.nlp.structure.Dictionary;
import dr.nlp.structure.Document;
import dr.nlp.structure.Sentence;
import dr.nlp.structure.impl.DocumentImpl;
import dr.nlp.token.Token;
import dr.nlp.token.type.TokenType;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public class DocumentParserImpl implements DocumentParser {
	Dictionary masterDictionary;
	// Would normally inject in from some framework like Spring and
	// configured it there.
	private DRNLPFactory factory = new DRNLPFactory();

	// Would normally inject in from some framework like Spring and
	// configured it there.
	private List<Processor<Character>> processors;

	// Would normally inject in from some framework like Spring and
	// configured it there.
	private Processor<Character> processor;

	// Would normally inject in from some framework like Spring and
	// configured it there.
	private Dictionary terminationPunctuationDictionary;

	public DocumentParserImpl(Dictionary masterDictionary) {
		this.masterDictionary = masterDictionary;
		processors = new ArrayList<>();
		try {
			processors.add(factory.createProperNounProcessor(masterDictionary));
		} catch (Exception e) {
			throw new RuntimeException("Failed to load ProperNounProcessor.",
					e);
		}
		processors.add(factory.createWordProcessor(masterDictionary));
		processors.add(factory.createPunctuationProcessor(masterDictionary));
		processors.add(factory.createWhiteSpaceProcessor(masterDictionary));

		processor = factory.createOrderedProcessor(processors);

		terminationPunctuationDictionary = factory.createDictionary();
		terminationPunctuationDictionary
				.putToken(new Token(".", TokenType.PUNCTUATION));
		terminationPunctuationDictionary
				.putToken(new Token("?", TokenType.PUNCTUATION));
		terminationPunctuationDictionary
				.putToken(new Token("!", TokenType.PUNCTUATION));
		terminationPunctuationDictionary
				.putToken(new Token(".\"", TokenType.PUNCTUATION));

	}

	@Override
	public Document parse(Path path) throws IOException {
		Document document;
		document = factory.createDocument();
		DataInput<Character> dataInput;

		dataInput = factory.createDataInput(path);

		Optional<Sentence> sentenceOpt;
		while ((sentenceOpt = parseNextSentence(dataInput)).isPresent()) {
			if (!sentenceOpt.get().getTokens().isEmpty()) {
				document.getSentences().add(sentenceOpt.get());
			}
		}

		return document;
	}

	private Optional<Sentence> parseNextSentence(DataInput dataInput) {
		Sentence sentence = factory.createSentence();
		Optional<Token> tokenOpt;
		if (dataInput.isEof()) {
			return Optional.empty();
		}
		try {
			while (!isSentenceTerminated(sentence)) {
				tokenOpt = processor.process(dataInput);
				sentence.getTokens().add(tokenOpt.get());
			}
		} catch (EOFException e) {
			// No more sentences
			return Optional.of(sentence);
		}
		return Optional.of(sentence);
	}

	private boolean isSentenceTerminated(Sentence sentence) {
		List<Token> tokens = sentence.getTokens();
		int size = tokens.size();
		if (size > 1) {
			Token last = tokens.get(size - 1);
			Token almostLast = tokens.get(size - 2);

			if (last.getType() == TokenType.WHITE_SPACE
					&& terminationPunctuationDictionary.contains(almostLast)) {
				return true;
			}
		}
		return false;
	}
}
