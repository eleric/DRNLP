package dr.nlp.processor.impl;

import dr.nlp.atom.impl.PunctuationAtom;

import dr.nlp.processor.Processor;
import dr.nlp.token.type.TokenType;

/**
 * Created by eleri_000 on 10/17/2015.
 */
public class PunctuationProcessor extends AbstractCharacterProcessor
		implements Processor<Character> {
	public PunctuationProcessor() {
		tokenAtom = new PunctuationAtom();
		tokenType = TokenType.PUNCTUATION;
	}
}
