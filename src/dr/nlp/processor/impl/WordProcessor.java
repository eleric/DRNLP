package dr.nlp.processor.impl;

import dr.nlp.atom.Atom;
import dr.nlp.atom.impl.PunctuationAtom;
import dr.nlp.atom.impl.WhiteSpaceAtom;
import dr.nlp.atom.impl.WordAtom;
import dr.nlp.dataInput.DataInput;
import dr.nlp.exception.WrongTypeException;
import dr.nlp.processor.Processor;
import dr.nlp.token.Token;
import dr.nlp.token.type.TokenType;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public class WordProcessor implements Processor<Character> {
	final Atom tokenAtom = new WordAtom();
//	final List<Atom> terminatorAtoms = new ArrayList<>();
//	{
//		terminatorAtoms.add(new WhiteSpaceAtom());
//		terminatorAtoms.add(new PunctuationAtom());
//	}

	@Override
	public Optional<Token> process(DataInput<Character> dataInput) throws
			EOFException {
		StringBuilder buf = new StringBuilder();
		// If dataInput is at end of index EOFException will be thrown by get()
		char firstEl = dataInput.get();
		if (!tokenAtom.isValid(firstEl))
		{
			// Can't process this element
			return Optional.empty();
		}
		else
		{
			buf.append(firstEl);
			while (true) {
				char el;
				try {
					el = dataInput.get();
					if (tokenAtom.isValid(el))
					{
						buf.append(el);
					}
					else
					{
						// Set index pointer back so it can be processed next.
						dataInput.stepBack();
						return makeToken(buf);
					}
				} catch (EOFException e) {
					return makeToken(buf);
				}
			}
		}
	}

	private Optional<Token> makeToken(StringBuilder buf)
	{
		return Optional.of(new Token(buf.toString(), TokenType.WORD));
	}
}
