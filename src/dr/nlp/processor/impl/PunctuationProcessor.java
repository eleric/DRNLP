package dr.nlp.processor.impl;

import dr.nlp.atom.impl.PunctuationAtom;

import dr.nlp.processor.AbstractCharacterProcessor;
import dr.nlp.processor.Processor;
import dr.nlp.structure.Dictionary;
import dr.nlp.token.type.TokenType;

/**
 * Created by eleri_000 on 10/17/2015.
 */
public class PunctuationProcessor extends AbstractCharacterProcessor
		implements Processor<Character> {
	public PunctuationProcessor(Dictionary masterDictionary) {
		super(masterDictionary);
		tokenAtom = new PunctuationAtom();
		tokenType = TokenType.PUNCTUATION;
	}
}
