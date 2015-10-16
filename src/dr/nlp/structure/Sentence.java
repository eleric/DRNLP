package dr.nlp.structure;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eleri_000 on 10/15/2015.
 */
public class Sentence {
	private final List<Token> tokens = new LinkedList<>();

	public List<Token> getTokens() {
		return tokens;
	}
}
