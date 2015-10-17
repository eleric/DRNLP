package dr.nlp.structure.impl;

import dr.nlp.structure.Sentence;
import dr.nlp.token.Token;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eleri_000 on 10/15/2015.
 */
@XmlRootElement(name = "sentence")
public class SentenceImpl implements Sentence {
	@XmlElement(name = "token")
	private final List<Token> tokens = new LinkedList<>();

	public List<Token> getTokens() {
		return tokens;
	}
}
