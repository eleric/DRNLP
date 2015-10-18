package dr.nlp.processor.impl;

import dr.nlp.atom.Atom;
import dr.nlp.dataInput.DataInput;
import dr.nlp.factory.DRNLPFactory;
import dr.nlp.processor.Processor;
import dr.nlp.token.Token;
import dr.nlp.token.type.TokenType;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by eleri_000 on 10/17/2015.
 */
public class ProperNounProcessor implements Processor<Character> {
	// In a production system, this would most likely be injected from
	// a framework.
	private final List<String> properNouns;
	private final DRNLPFactory factory = new DRNLPFactory();
	private Atom whitSpaceAtom = factory.createWhiteSpaceAtom();
	private Atom punctuationAtom = factory.createPunctuationAtom();

	public ProperNounProcessor() throws URISyntaxException, IOException {
		this.properNouns = new ArrayList<>();
		URL url  = this.getClass().getClassLoader().
				getResource("resources/NER.txt");
		Path path = Paths.get(url.toURI());
		BufferedReader bf = new BufferedReader(new FileReader(path.toFile()));
		for (String line = bf.readLine(); line != null; line = bf.readLine())
		{
			properNouns.add(line);
		}
	}

	@Override
	public Optional<Token> process(DataInput<Character> dataInput)
			throws EOFException {
		StringBuilder buf = new StringBuilder();
		int rollbackIndex = dataInput.getIndex();
		while (true)
		{
			buf.append(dataInput.get());
			final String search = buf.toString();
			// If search matches any proper noun then return token
			if (properNouns.stream().anyMatch((p)->p.equals(search)))
			{
				char next = dataInput.get();
				dataInput.stepBack();
				// Proper Noun needs to be separated by white space or punctuation
				// to be valid.
				if (whitSpaceAtom.isValid(next)||punctuationAtom.isValid(next)) {
					return makeToken(search);
				}
			}
			// If current search string no longer has a chance of matching a
			// proper noun. Rollback index and return empty token.
			if (properNouns.stream().filter((p)->p.startsWith(search)).count() == 0)
			{
				dataInput.setIndex(rollbackIndex);
				return Optional.empty();
			}
			// Else keep searching
		}
	}

	private Optional<Token> makeToken(String search)
	{
		return Optional.of(new Token(search, TokenType.PROPER_NOUN));
	}

}
