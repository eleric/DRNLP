package dr.nlp.structure.impl;

import dr.nlp.structure.Sentence;
import dr.nlp.token.Token;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eleri_000 on 10/15/2015.
 */
public class SentenceImpl implements Sentence {
	private final List<Token> tokens = new LinkedList<>();

	public List<Token> getTokens() {
		return tokens;
	}
}
