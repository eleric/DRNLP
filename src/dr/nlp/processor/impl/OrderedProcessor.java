package dr.nlp.processor.impl;

import dr.nlp.dataInput.DataInput;
import dr.nlp.processor.Processor;
import dr.nlp.token.Token;
import dr.nlp.token.type.TokenType;

import java.io.EOFException;
import java.util.List;
import java.util.Optional;

/**
 * Iterates through an ordered list of processors and returns the token
 * of the first processor able to process it.  If processors unable to
 * process token, token of unknown type is returned.
 * Created by eleri_000 on 10/17/2015.
 */
public class OrderedProcessor implements Processor<Character> {
	private final List<Processor<Character>> processors;

	public OrderedProcessor(List<Processor<Character>> processors) {
		this.processors = processors;
	}

	@Override
	public Optional<Token> process(DataInput<Character> dataInput)
			throws EOFException {
		Optional<Token> tokenOpt;

		for (Processor<Character> p : processors)
		{
			tokenOpt = p.process(dataInput);
			if (tokenOpt.isPresent())
			{
				return tokenOpt;
			}
		}
		Optional<Token> unknown = Optional.of(new Token(dataInput.get().toString(), TokenType.UNKNOWN));
		dataInput.stepBack();
		return unknown;
	}
}
