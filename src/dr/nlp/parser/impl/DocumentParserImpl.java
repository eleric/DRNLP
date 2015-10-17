package dr.nlp.parser.impl;

import dr.nlp.dataInput.DataInput;
import dr.nlp.dataInput.impl.InMemoryArrayDataInput;
import dr.nlp.factory.DRNLPFactory;
import dr.nlp.parser.DocumentParser;
import dr.nlp.processor.Processor;
import dr.nlp.processor.impl.WordProcessor;
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
	// Would normally passed in from some framework like Spring and
	// configured it there.
	private DRNLPFactory factory = new DRNLPFactory();

	// Would normally passed in from some framework like Spring and
	// configured it there.
	private List<Processor<Character>> processors;
	{
		processors = new ArrayList<>();
		processors.add(factory.createWordProcessor());
	}

	// Would normally passed in from some framework like Spring and
	// configured it there.
	private Processor<Character> processor = factory.createOrderedProcessor(processors);

	@Override
	public Document parse(Path path) throws IOException {
		Document document;
		document = factory.createDocument();
		DataInput<Character> dataInput;

		dataInput = factory.createDataInput(path);

		Optional<Sentence> sentenceOpt;
		while ((sentenceOpt=parseNextSentence(dataInput)).isPresent())
		{
			if (!sentenceOpt.get().getTokens().isEmpty()) {
				document.getSentences().add(sentenceOpt.get());
			}
		}

		return document;
	}

	private Optional<Sentence> parseNextSentence(DataInput dataInput) {
		Sentence sentence = factory.createSentence();
		Optional<Token> tokenOpt;
		if (dataInput.isEof())
		{
			return Optional.empty();
		}
		try {
			while (!isSentenceTerminator(
					tokenOpt = processor.process(dataInput))) {
				sentence.getTokens().add(tokenOpt.get());
			}
		} catch (EOFException e)
		{
			// No more sentences
			return Optional.of(sentence);
		}
		return Optional.of(sentence);
	}

	private boolean isSentenceTerminator(Optional<Token> tokenOpt)
	{
		if (tokenOpt.isPresent())
		{
			if (tokenOpt.get().getType() != TokenType.WORD)
			{
				return true;
			}
		}
		return false;
	}
}
