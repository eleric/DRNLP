package dr.nlp.structure;

import dr.nlp.token.Token;

import java.util.List;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public interface Sentence {
	/**
	 * Lists tokens in order for a given sentence
	 *
	 * @return
	 */
	List<Token> getTokens();
}
